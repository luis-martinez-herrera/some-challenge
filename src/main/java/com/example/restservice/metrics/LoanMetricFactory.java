package com.example.restservice.metrics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;

@Service
public class LoanMetricFactory {

   public static final String LOAN_TYPE_STUDENT = "student";
   public static final String LOAN_TYPE_CONSUMER = "consumer";

   private final ILoanMetricCalculator consumer;
   private final ILoanMetricCalculator student;

   public LoanMetricFactory(@Qualifier("ConsumerLoanMetricCalculator") ILoanMetricCalculator consumer,
                            @Qualifier("StudentLoanMetricCalculator") ILoanMetricCalculator student) {
      this.consumer = consumer;
      this.student = student;
   }

   public ILoanMetricCalculator getInstance(Loan loan) {
      if (LOAN_TYPE_CONSUMER.equals(loan.getType())) {
         return consumer;
      }
      return student;
   }

}
