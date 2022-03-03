package com.example.restservice.metrics;

import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator;
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratorUtil;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoanMetricFactoryTest {

    LoanMetricCalculator consumer = new ConsumerLoanMetricCalculator();
    LoanMetricCalculator student = new StudentLoanMetricCalculator();

    private LoanMetricFactory loanMetricFactory = new LoanMetricFactory(consumer, student);

    @Test
    void testConsumerInstance() {
        Loan loan = LoanGeneratorUtil.createLoan(10L);
        loan.setType(LoanMetricFactory.LOAN_TYPE_CONSUMER);

        LoanMetricCalculator result = loanMetricFactory.getInstance(loan);

        Assertions.assertEquals(ConsumerLoanMetricCalculator.class, result.getClass());
    }

    @Test
    void testStudentInstance() {
        Loan loan = LoanGeneratorUtil.createLoan(10L);
        loan.setType(LoanMetricFactory.LOAN_TYPE_STUDENT);

        loan.getBorrower().setAge(18);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {loanMetricFactory.getInstance(loan);});

        loan.getBorrower().setAge(30);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {loanMetricFactory.getInstance(loan);});

        loan.getBorrower().setAge(25);
        LoanMetricCalculator result = loanMetricFactory.getInstance(loan);
        Assertions.assertEquals(StudentLoanMetricCalculator.class, result.getClass());
    }
}