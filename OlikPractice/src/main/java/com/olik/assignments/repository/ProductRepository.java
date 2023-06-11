package com.olik.assignments.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olik.assignments.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findAvailableProducts(LocalDate from, LocalDate to);
}