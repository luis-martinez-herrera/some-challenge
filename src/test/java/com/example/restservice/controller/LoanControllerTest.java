package com.example.restservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
}