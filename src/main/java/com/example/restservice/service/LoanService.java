package com.example.restservice.service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

public interface LoanService {
    Loan getLoan(Long id);

    LoanMetric calculateLoanMetric(Long loanId);

    Loan getMaxMonthlyPaymentLoan();
}
