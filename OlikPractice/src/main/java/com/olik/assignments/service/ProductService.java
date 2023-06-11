package com.olik.assignments.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olik.assignments.model.Booking;
import com.olik.assignments.model.Product;
import com.olik.assignments.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*Accessing and collecting all products according to availability*/
    public List<Product> getAllAvailableProducts(LocalDate from, LocalDate to) {
        List<Product> allProducts = productRepository.findAll();
        
        List<Product> availableProducts = allProducts.stream()
                .filter(product -> isProductAvailable(product, from, to))
                .collect(Collectors.toList());
        
        return availableProducts;
    }

    /*Checking a product's availability*/
    private boolean isProductAvailable(Product product, LocalDate from, LocalDate to) {
        for (Booking booking : product.getBookings()) {
            if (booking.getBookedFrom().isBefore(to) && booking.getBookedTo().isAfter(from)) {
                return false;
            }
        }
        return true;
    }

    /*Checking a product's availability by id*/
    public Optional<Product> getAvailableProductById(Long productId, LocalDate from, LocalDate to) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            for (Booking booking : product.getBookings()) {
                if (booking.getBookedFrom().isBefore(to) && booking.getBookedTo().isAfter(from)) {
                    return Optional.empty();
                }
            }
            return optionalProduct;
        }
        return Optional.empty();
    }

    /*Saving a product to Database*/
    public Product saveProduct(Product product) {
        if (product.getBookings() != null) {
            for (Booking booking : product.getBookings()) {
                booking.setProduct(product);
            }
        }
        return productRepository.save(product);
    }


    /*Adding a booking to a product*/
    public Product addBookingToProduct(Long productId, Booking booking) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.getBookings().add(booking);
            return productRepository.save(product);
        }
        throw new RuntimeException("Product not found");
    }
}
