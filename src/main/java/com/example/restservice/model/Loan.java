package com.example.restservice.model;

import lombok.Data;

@Data
public class Loan {

	private Long loanId;
	private Double requestedAmount;
	private Integer termMonths;
	private Double annualInterest;
	private String type;
	private Borrower borrower;

}