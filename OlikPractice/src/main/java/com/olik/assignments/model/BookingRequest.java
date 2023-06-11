package com.olik.assignments.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
	  
	  private Long productId;
	  private LocalDate startDate;
	  private LocalDate endDate;
	
	  public BookingRequest() {
			super();
			// TODO Auto-generated constructor stub
		}

	public BookingRequest(Long productId, LocalDate startDate, LocalDate endDate) {
		super();
		this.productId = productId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	  
	  
	  
	  
	  
}
