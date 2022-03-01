package com.example.restservice.metrics.impl;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class StudentLoanMetricCalculatorTest {

    ILoanMetricCalculator metricCalculator = new StudentLoanMetricCalculator();

    @Test
    void testLoanMetric() {
        Loan loan = LoanGeneratorUtil.createLoan(2L);
        loan.setRequestedAmount(1000d);
        Assertions.assertEquals(LoanMetricFactory.LOAN_TYPE_STUDENT, loan.getType());

        LoanMetric loanMetric = metricCalculator.getLoanMetric(loan);

        Assertions.assertEquals(0.005, loanMetric.getMonthlyInterestRate());
    }
}