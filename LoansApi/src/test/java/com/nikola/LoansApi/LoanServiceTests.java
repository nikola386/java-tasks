package com.nikola.LoansApi;

import com.nikola.LoansApi.enums.UserRole;
import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.LoanRequest;
import com.nikola.LoansApi.models.Payment;
import com.nikola.LoansApi.repositories.LoanRepository;
import com.nikola.LoansApi.services.AccountService;
import com.nikola.LoansApi.services.LoanServiceImpl;
import com.nikola.LoansApi.services.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.nikola.LoansApi.utils.Mocks.mockedLoan;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTests {
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private PaymentService paymentService;
    @Mock
    private AccountService accountService;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    void whenCreateLoan_thenReturnNewLoan() {
        Account account = new Account("user", "pass", UserRole.USER);
        account.setId(1L);

        Loan expected = new Loan(BigDecimal.valueOf(1), 1, BigDecimal.valueOf(1));
        expected.setPayments(List.of(new Payment()));
        expected.setAccount(account);

        when(accountService.getById(any(Long.class))).thenReturn(account);
        when(paymentService.createPayments(any(Loan.class))).thenReturn(expected.getPayments());
        when(loanRepository.save(any(Loan.class))).thenReturn(expected);

        Loan actual = loanService.createLoan(account.getId(),
                new LoanRequest(BigDecimal.valueOf(1), 1, BigDecimal.valueOf(1)));

        assertThat(actual.getAmount()).isEqualTo(BigDecimal.valueOf(1));
        assertThat(actual.getTerm()).isEqualTo(1);
        assertThat(actual.getInterestRate()).isEqualTo(BigDecimal.valueOf(1));
        assertThat(actual.getPayments().size()).isEqualTo(1);
        assertThat(actual.getAccount()).usingRecursiveComparison().isEqualTo(account);

        verify(accountService, times(1)).getById(any(Long.class));
        verify(loanRepository, times(1)).save(any(Loan.class));
        verify(paymentService, times(1)).createPayments(any(Loan.class));

        verifyNoMoreInteractions(loanRepository);
        verifyNoMoreInteractions(accountService);
        verifyNoMoreInteractions(paymentService);
    }

    @Test
    void whenCreateLoan_andAccountHasLoan_thenThrowRuntimeException() {
        Account account = new Account("user", "pass", UserRole.USER, new Loan());
        when(accountService.getById(1L)).thenReturn(account);

        Assertions.assertThrows(RuntimeException.class, () -> loanService.createLoan(1L,
                new LoanRequest(BigDecimal.valueOf(1), 1, BigDecimal.valueOf(1))));

        verify(accountService, times(1)).getById(1L);
        verifyNoMoreInteractions(accountService);
    }

    @Test
    void whenGetLoan_thenReturnLoan() {
        Loan expected = mockedLoan();
        when(loanRepository.findById(1L)).thenReturn(Optional.of(expected));

        Loan actual = loanService.getLoan(1L);

        verify(loanRepository, times(1)).findById(1L);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verifyNoMoreInteractions(loanRepository);
    }

    @Test
    void whenGetLoan_andNoFound_thenThrowNotFound() {
        when(loanRepository.findById(1L)).thenThrow(NotFoundException.class);

        Assertions.assertThrows(NotFoundException.class, () -> loanService.getLoan(1L));
        verify(loanRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(loanRepository);
    }

    @Test
    void wheGetLoanByAccountId_thenReturnLoan() {
        Loan expected = mockedLoan();
        when(loanRepository.findByAccountId(1L)).thenReturn(Optional.of(expected));

        Loan actual = loanService.getLoanByAccountId(1L);

        verify(loanRepository, times(1)).findByAccountId(1L);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verifyNoMoreInteractions(loanRepository);
    }

    @Test
    void whenGetLoanByAccountId_andNoFound_thenThrowNotFound() {
        when(loanRepository.findByAccountId(1L)).thenThrow(NotFoundException.class);

        Assertions.assertThrows(NotFoundException.class, () -> loanService.getLoanByAccountId(1L));
        verify(loanRepository, times(1)).findByAccountId(1L);
        verifyNoMoreInteractions(loanRepository);
    }
}
