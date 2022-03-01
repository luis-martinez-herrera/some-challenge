package com.example.restservice.controller;

import com.example.restservice.service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@RestController
@RequestMapping("/loans")
public class LoanController {

	private final LoanService loanService;

	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@GetMapping("/{loanId}")
	public Loan getLoan(@PathVariable Long loanId) {
		return loanService.getLoan(loanId);
	}

	@GetMapping("/{loanId}/loan-metric")
	public LoanMetric calculateLoanMetric(@PathVariable Long loanId) {
		return loanService.calculateLoanMetric(loanId);
	}

	public Loan getMaxMonthlyPaymentLoan() {
		// Call LoanService
		return null;
	}

}
