package com.example.restservice.service;

import java.util.List;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.metrics.LoanMetricFactory;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratorUtil;

@Service
public class LoanServiceImpl implements LoanService {

	private final LoanMetricFactory loanMetricFactory;

	public LoanServiceImpl(LoanMetricFactory loanMetricFactory) {
		this.loanMetricFactory = loanMetricFactory;
	}

	@Override
	public Loan getLoan(Long id) {
		return LoanGeneratorUtil.createLoan(id);
	}

	@Override
	public LoanMetric calculateLoanMetric(Long loanId) {
		Loan loan = getLoan(loanId);

		ILoanMetricCalculator metricCalculator = loanMetricFactory.getInstance(loan);

		return metricCalculator.getLoanMetric(loan);
	}

	@Override
	public Loan getMaxMonthlyPaymentLoan() {
		List<Loan> allLoans = LoanGeneratorUtil.getRandomLoans(20L);
		// get the loan with the max monthly payment
		return null;
	}
}
