package com.example.restservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@RestController
@RequestMapping("/loans")
public class LoanController {

	@GetMapping("/{loanId}")
	public Loan getLoan(@PathVariable Long loanId) {
		return null;
	}


	public LoanMetric calculateLoanMetric(Long loanId) {
		// Call LoanService
		return null;
	}

	public LoanMetric calculateLoanMetric(Loan loan) {
		// Call LoanService
		return null;
	}

	public Loan getMaxMonthlyPaymentLoan() {
		// Call LoanService
		return null;
	}

}
