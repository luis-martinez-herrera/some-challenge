package com.example.restservice.controller;

import com.example.restservice.model.Loan;
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
    void testBadRequest() throws Exception{
        this.mockMvc.perform(get("/loans/s")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void testRequest() throws Exception{
        this.mockMvc.perform(get("/loans/1")).andDo(print()).andExpect(status().isOk());
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
}