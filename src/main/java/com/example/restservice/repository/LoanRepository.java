package com.example.restservice.repository;

import com.example.restservice.model.Loan;

import java.util.List;

public interface LoanRepository {
    Loan getLoanById (Long loanId);

    List<Loan> getMonthlyLoans ();
}
