package com.example.restservice.metrics.impl;

import com.example.restservice.metrics.LoanMetricCalculator;
import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentLoanMetricCalculatorTest {

    private LoanMetricCalculator metricCalculator = new StudentLoanMetricCalculator();

    @Test
    void testLoanMetric() {
        Loan loan = LoanGeneratorUtil.createLoan(1L);
        loan.setType(LoanMetricFactory.LOAN_TYPE_STUDENT);
        loan.setAnnualInterest(6.0);
        loan.setTermMonths(24);

        LoanMetric loanMetric = metricCalculator.getLoanMetric(loan);

        Assertions.assertEquals(0.005, loanMetric.getMonthlyInterestRate());
        Assertions.assertEquals(354.56, loanMetric.getMonthlyPayment());
    }
}