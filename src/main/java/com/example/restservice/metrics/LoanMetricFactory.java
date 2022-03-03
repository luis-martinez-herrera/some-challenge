package com.example.restservice.metrics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;

@Service
public class LoanMetricFactory {

   public static final String LOAN_TYPE_STUDENT = "student";
   public static final String LOAN_TYPE_CONSUMER = "consumer";

   private final LoanMetricCalculator consumer;
   private final LoanMetricCalculator student;

   public LoanMetricFactory(@Qualifier("ConsumerLoanMetricCalculator") LoanMetricCalculator consumer,
                            @Qualifier("StudentLoanMetricCalculator") LoanMetricCalculator student) {
      this.consumer = consumer;
      this.student = student;
   }

   public LoanMetricCalculator getInstance(Loan loan) {
      if (LOAN_TYPE_CONSUMER.equals(loan.getType())) {
         return consumer;
      }
      return student;
   }

}
