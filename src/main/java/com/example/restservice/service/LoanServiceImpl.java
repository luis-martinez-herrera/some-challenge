package com.example.restservice.service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.restservice.metrics.LoanMetricCalculator;
import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.repository.LoanRepository;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Service
public class LoanServiceImpl implements LoanService {

	private final LoanMetricFactory loanMetricFactory;
	private final LoanRepository loanRepository;

	public LoanServiceImpl(LoanMetricFactory loanMetricFactory, LoanRepository loanRepository) {
		this.loanMetricFactory = loanMetricFactory;
		this.loanRepository = loanRepository;
	}

	@Override
	public Loan getLoan(Long loanId) {
		return loanRepository.getLoanById(loanId);
	}

	@Override
	public LoanMetric calculateLoanMetric(Long loanId) {
		Loan loan = getLoan(loanId);

		return calculateLoanMetric(loan);
	}

	private LoanMetric calculateLoanMetric(Loan loan) {
		LoanMetricCalculator metricCalculator = loanMetricFactory.getInstance(loan);

		return metricCalculator.getLoanMetric(loan);
	}

	@Override
	public Loan getMaxMonthlyPaymentLoan() {
		List<Loan> monthlyLoans = loanRepository.getMonthlyLoans();
		return monthlyLoans.stream()
				.max(Comparator.comparing(loan -> calculateLoanMetric(loan).getMonthlyPayment()))
				.orElseThrow(NoSuchElementException::new);

	}
}
