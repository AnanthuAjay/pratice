package com.olik.assignments.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olik.assignments.model.Booking;
import com.olik.assignments.model.Product;
import com.olik.assignments.repository.ProductRepository;
import com.olik.assignments.service.ProductService;

/*This is our Controller class*/
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired /*Service class Object*/
	ProductService productService;

	
    @GetMapping /*API ENDPOINT to collect all available products*/
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        List<Product> products = productService.getAllAvailableProducts(from, to);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping /*API ENDPOINT to Creating Products*/
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    	Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @PostMapping("/{productId}/book") /*API ENDPOINT to book a Product*/
    public ResponseEntity<String> bookProduct(
            @PathVariable("productId") Long productId,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
    	
        Optional<Product> optionalProduct = productService.getAvailableProductById(productId, from, to);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Booking booking = new Booking();
            booking.setBookedFrom(from);
            booking.setBookedTo(to);
            double addedBookingToProduct = productService.addBookingToProduct(productId, booking);
            return new ResponseEntity<>("Booking successful\nTotal Cost="+addedBookingToProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not available for the selected dates", HttpStatus.BAD_REQUEST);
        }
    }
}
