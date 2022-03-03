package com.example.restservice.metrics.impl;

import com.example.restservice.util.Formatter;
import org.springframework.stereotype.Component;

import com.example.restservice.metrics.LoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Component("StudentLoanMetricCalculator")
public class StudentLoanMetricCalculator implements LoanMetricCalculator {

	@Override
	public LoanMetric getLoanMetric(Loan loan) {
		double monthlyInterestRate = (loan.getAnnualInterest() /12 ) / 100;

		double monthlyPayment = 0.8 * (loan.getRequestedAmount() * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate,((-1) * loan.getTermMonths()) ));

		return new LoanMetric (Formatter.formatToThreeDecimals(monthlyInterestRate), Formatter.formatToTwoDecimals(monthlyPayment));
	}

	@Override
	public boolean isSupported (Loan loan){
		return loan.getBorrower().getAge() > 18 && loan.getBorrower().getAge() < 30;
	}
}
