package com.example.restservice.service;

import com.example.restservice.RestServiceApplication;
import com.example.restservice.metrics.LoanMetricCalculator;
import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.model.Loan;
import com.example.restservice.repository.LoanRepository;
import com.example.restservice.util.LoanGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    @Mock
    private LoanMetricFactory loanMetricFactory;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    void getLoan() {
    }

    @Test
    void calculateLoanMetric() {
    }

    @Test
    void getMaxMonthlyPaymentLoan() {
        List<Loan> loans = new ArrayList<>(10);

        Loan loan = LoanGeneratorUtil.createLoan(10L);
        loan.setType(LoanMetricFactory.LOAN_TYPE_STUDENT);
        loan.setAnnualInterest(6.0);
        loan.setTermMonths(24);

        loans.add(loan);

        Mockito.when(loanRepository.getMonthlyLoans()).thenReturn(loans);
        Loan response = loanService.getMaxMonthlyPaymentLoan();

        Assertions.assertEquals(loan.getLoanId(), response.getLoanId());
    }

    @Test
    void getMaxMonthlyPaymentLoanWithMultipleElements() {
        List<Loan> loans = new ArrayList<>(3);

        Loan loan1 = LoanGeneratorUtil.createLoan(10L);
        loans.add(loan1);

        Loan loan2 = LoanGeneratorUtil.createLoan(9L);
        loans.add(loan2);

        Loan loan3 = LoanGeneratorUtil.createLoan(8L);
        loans.add(loan3);

        Mockito.when(loanRepository.getMonthlyLoans()).thenReturn(loans);

        LoanMetricCalculator metricCalculator = Mockito.mock(LoanMetricCalculator.class, Mockito.RETURNS_DEEP_STUBS);

        Mockito.when(metricCalculator.getLoanMetric(Mockito.eq(loan1)).getMonthlyPayment()).thenReturn(50.0);
        Mockito.when(metricCalculator.getLoanMetric(Mockito.eq(loan2)).getMonthlyPayment()).thenReturn(69.0);
        Mockito.when(metricCalculator.getLoanMetric(Mockito.eq(loan3)).getMonthlyPayment()).thenReturn(51.0);
        Mockito.when(loanMetricFactory.getInstance(Mockito.any())).thenReturn(metricCalculator);
        Loan response = loanService.getMaxMonthlyPaymentLoan();

        Assertions.assertNotEquals(loan1.getLoanId(), response.getLoanId());
        Assertions.assertEquals(loan2.getLoanId(), response.getLoanId());
        Assertions.assertNotEquals(loan3.getLoanId(), response.getLoanId());

        Mockito.when(metricCalculator.getLoanMetric(Mockito.eq(loan1)).getMonthlyPayment()).thenReturn(50.0);
        Mockito.when(metricCalculator.getLoanMetric(Mockito.eq(loan2)).getMonthlyPayment()).thenReturn(51.0);
        Mockito.when(metricCalculator.getLoanMetric(Mockito.eq(loan3)).getMonthlyPayment()).thenReturn(69.0);
        Mockito.when(loanMetricFactory.getInstance(Mockito.any())).thenReturn(metricCalculator);
        Loan response2 = loanService.getMaxMonthlyPaymentLoan();

        Assertions.assertNotEquals(loan1.getLoanId(), response2.getLoanId());
        Assertions.assertNotEquals(loan2.getLoanId(), response2.getLoanId());
        Assertions.assertEquals(loan3.getLoanId(), response2.getLoanId());
    }
}