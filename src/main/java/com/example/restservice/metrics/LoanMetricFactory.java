package com.example.restservice.metrics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;

@Service
@Slf4j
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
         return validateSupport(consumer, loan);
      }

      return validateSupport(student, loan);
   }

  private LoanMetricCalculator validateSupport (LoanMetricCalculator metricCalculator, Loan loan){
      if (metricCalculator.isSupported(loan)) {
         return metricCalculator;
      }
      log.error("Invalid borrower: {}", loan);
      throw new IllegalArgumentException("Invalid borrower");
   }
}
