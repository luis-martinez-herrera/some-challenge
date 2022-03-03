package com.example.restservice.repository;

import com.example.restservice.model.Loan;
import com.example.restservice.util.LoanGeneratorUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanRepositoryImpl implements LoanRepository {
    @Override
    public Loan getLoanById(Long loanId) {
        return LoanGeneratorUtil.createLoan(loanId);
    }

    @Override
    public List<Loan> getMonthlyLoans() {
        return LoanGeneratorUtil.getRandomLoans(20);
    }
}
