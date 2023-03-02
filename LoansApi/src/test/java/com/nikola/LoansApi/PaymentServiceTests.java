package com.nikola.LoansApi;

import com.nikola.LoansApi.enums.UserRole;
import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.LoanRequest;
import com.nikola.LoansApi.models.Payment;
import com.nikola.LoansApi.repositories.LoanRepository;
import com.nikola.LoansApi.repositories.PaymentRepository;
import com.nikola.LoansApi.services.AccountService;
import com.nikola.LoansApi.services.LoanServiceImpl;
import com.nikola.LoansApi.services.PaymentService;
import com.nikola.LoansApi.services.PaymentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.nikola.LoansApi.utils.Mocks.mockedLoan;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    public void whenCreatePayments_thenReturnListOfPayments() {
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        Loan loan = new Loan(BigDecimal.valueOf(100), 2, BigDecimal.valueOf(1));

        List<Payment> actual = paymentService.createPayments(loan);
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getAmount()).isEqualTo(BigDecimal.valueOf(56.08));
        assertThat(actual.get(0).getDueDate()).isEqualTo(expectedDate);

        verify(paymentRepository, times(1)).saveAll(any());
        verifyNoMoreInteractions(paymentRepository);
    }
}
