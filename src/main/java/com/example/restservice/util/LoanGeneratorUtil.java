package com.example.restservice.util;

import java.util.ArrayList;
import java.util.List;

import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.model.Borrower;
import com.example.restservice.model.Loan;

public class LoanGeneratorUtil {

	public static Loan createLoan(Long loanId) {
		String loanType = loanId % 2 == 0 ? LoanMetricFactory.LOAN_TYPE_STUDENT : LoanMetricFactory.LOAN_TYPE_CONSUMER;
		Borrower borrower = new Borrower();
		borrower.setName("Borrower ".concat(loanId.toString()));
		borrower.setAge(23);
		borrower.setAnnualIncome(150000D);
		borrower.setDelinquentDebt(true);
		borrower.setAnnualDebt(1200D);
		borrower.setCreditHistory(50);

		Loan loan = new Loan();
		loan.setLoanId(loanId);
		loan.setRequestedAmount(10000D * loanId);
		loan.setTermMonths(loanId % 2 == 0 ? 36 : 60);
		loan.setAnnualInterest(6.0);
		loan.setType(loanType);
		loan.setBorrower(borrower);

		return loan;
	}
	
	public static List<Loan> getRandomLoans(int numberOfLoans) {
		List<Loan> loans = new ArrayList<>();
		for (long x = 1; x <= numberOfLoans; x++) {
			loans.add(createLoan(x));
		}
		return loans;
	}
}
