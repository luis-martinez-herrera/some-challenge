package com.example.restservice.controller;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.service.LoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoanController loanController;

    @MockBean
    private LoanService loanService;

    @Test
    void testInvalidUrl() throws Exception{
        this.mockMvc.perform(get("/loans")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void testGetLoanBadRequest() throws Exception{
        this.mockMvc.perform(get("/loans/s")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void testGetLoanOkRequest() throws Exception{
        this.mockMvc.perform(get("/loans/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testLoanMetricBadRequest() throws Exception{
        this.mockMvc.perform(get("/loans/s/loan-metric")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void testLoanMetricOkRequest() throws Exception{
        this.mockMvc.perform(get("/loans/1/loan-metric")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testMaximumMonthlyPaymentOkRequest() throws Exception{
        this.mockMvc.perform(get("/loans/maximum-monthly-payment")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetLoan() {
        Long loanId = ThreadLocalRandom.current().nextLong();

        Loan loan = new Loan();
        loan.setLoanId(loanId);

        Mockito.when(loanService.getLoan(Mockito.anyLong())).thenReturn(loan);

        Loan response = loanController.getLoan(loanId);

        Assertions.assertEquals(loanId, response.getLoanId());
    }

    @Test
    void testCalculateLoanMetric() {
        LoanMetric loanMetric = new LoanMetric(0.005, 443);
        Mockito.when(loanService.calculateLoanMetric(Mockito.anyLong())).thenReturn(loanMetric);

        LoanMetric response = loanController.calculateLoanMetric(1L);

        Assertions.assertEquals(0.005, response.getMonthlyInterestRate());
        Assertions.assertEquals(443, response.getMonthlyPayment());
    }

    @Test
    void testMaxMonthlyPaymentLoan() {
        Long loanId = ThreadLocalRandom.current().nextLong();

        Loan loan = new Loan();
        loan.setLoanId(loanId);

        Mockito.when(loanService.getMaxMonthlyPaymentLoan()).thenReturn(loan);

        Loan response = loanController.getMaxMonthlyPaymentLoan();

        Assertions.assertEquals(loanId, response.getLoanId());
    }
}