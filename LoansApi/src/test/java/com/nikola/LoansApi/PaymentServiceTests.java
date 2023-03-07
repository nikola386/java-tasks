package com.nikola.LoansApi;

import com.nikola.LoansApi.enums.PaymentStatus;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.Payment;
import com.nikola.LoansApi.repositories.LoanRepository;
import com.nikola.LoansApi.repositories.PaymentRepository;
import com.nikola.LoansApi.services.LoanService;
import com.nikola.LoansApi.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PaymentServiceTests {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private LoanRepository loanRepository;

    @Test
    public void whenCreatePayments_thenReturnListOfPayments() {
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        Loan loan = new Loan(BigDecimal.valueOf(100), 2, BigDecimal.valueOf(1));
        loan = loanRepository.save(loan);

        List<Payment> actual = paymentService.createPayments(loan);
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getAmount()).isEqualTo(BigDecimal.valueOf(56.08));
        assertThat(actual.get(0).getDueDate()).isEqualTo(expectedDate);
    }

    @Test
    public void whenMakePayment_thenStatusShouldBePaid() {
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        Payment expected = new Payment(expectedDate, BigDecimal.valueOf(1), null);
        expected = paymentRepository.save(expected);

        Payment actual = paymentService.makePayment(expected.getId());

        assertThat(actual.getStatus()).isEqualTo(PaymentStatus.PAID);
    }

    @Test
    public void whenForgivePayment_thenStatusShouldBeForgiven() {
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        Payment expected = new Payment(expectedDate, BigDecimal.valueOf(1), null);
        expected = paymentRepository.save(expected);

        Payment actual = paymentService.forgivePayment(expected.getId());

        assertThat(actual.getStatus()).isEqualTo(PaymentStatus.FORGIVEN);
    }
}
