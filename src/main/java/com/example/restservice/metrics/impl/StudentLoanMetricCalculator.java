package com.example.restservice.metrics.impl;

import org.springframework.stereotype.Component;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Component("StudentLoanMetricCalculator")
public class StudentLoanMetricCalculator extends AbstractMetricCalculator implements ILoanMetricCalculator {

	@Override
	public LoanMetric getLoanMetric(Loan loan) {
		double monthlyInterestRate = (loan.getAnnualInterest() /12 ) / 100;

		double monthlyPayment = 0.8 * (loan.getRequestedAmount() * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate,((-1) * loan.getTermMonths()) ));

		return new LoanMetric (monthlyInterestRate, super.format(monthlyPayment));
	}

}
