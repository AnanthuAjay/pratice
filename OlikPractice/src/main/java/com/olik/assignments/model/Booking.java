package com.olik.assignments.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "booking", indexes = @Index(name = "idx_booking_id", columnList = "id"))
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

    private LocalDate bookedFrom;
    private LocalDate bookedTo;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(Long id, LocalDate bookedFrom, LocalDate bookedTo, Product product) {
		super();
		this.id = id;
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getBookedFrom() {
		return bookedFrom;
	}

	public void setBookedFrom(LocalDate bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	public LocalDate getBookedTo() {
		return bookedTo;
	}

	public void setBookedTo(LocalDate bookedTo) {
		this.bookedTo = bookedTo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
    
    
    
}