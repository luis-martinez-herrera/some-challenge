package com.example.restservice.metrics.impl;

import org.springframework.stereotype.Component;

import com.example.restservice.metrics.LoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Component("ConsumerLoanMetricCalculator")
public class ConsumerLoanMetricCalculator extends AbstractMetricCalculator implements LoanMetricCalculator {

	@Override
	public LoanMetric getLoanMetric(Loan loan) {
		double monthlyInterestRate = (loan.getAnnualInterest() /12 ) / 100;

		double monthlyPayment = (loan.getRequestedAmount() * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate,((-1) * loan.getTermMonths()) ));

		return new LoanMetric (monthlyInterestRate, super.format(monthlyPayment));
	}

}
