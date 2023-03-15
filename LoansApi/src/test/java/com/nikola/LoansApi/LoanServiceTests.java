package com.nikola.LoansApi;

import com.nikola.LoansApi.enums.UserRole;
import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.dto.LoanRequest;
import com.nikola.LoansApi.repositories.AccountRepository;
import com.nikola.LoansApi.repositories.LoanRepository;
import com.nikola.LoansApi.services.LoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.nikola.LoansApi.utils.Mocks.mockedLoan;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanServiceTests {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanService loanService;

    @Test
    void whenCreateLoan_thenReturnNewLoan() {
        Account account = new Account("user", "pass", UserRole.USER);
        account = accountRepository.save(account);

        Loan actual = loanService.createLoan(account.getId(),
                new LoanRequest(BigDecimal.valueOf(1), 1));

        assertThat(actual.getAmount()).isEqualTo(BigDecimal.valueOf(1));
        assertThat(actual.getTerm()).isEqualTo(1);
        assertThat(actual.getInterestRate()).isEqualTo(BigDecimal.valueOf(5.8));
        assertThat(actual.getPayments().size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void whenGetLoan_thenReturnLoan() {
        Loan expected = mockedLoan();
        expected.setId(1L);
        loanRepository.save(expected);

        Loan actual = loanService.getLoan(1L);

        assertEquals(0, actual.getAmount().compareTo(expected.getAmount()));
        assertEquals(0, actual.getInterestRate().compareTo(expected.getInterestRate()));
        assertThat(actual.getTerm()).isEqualTo(expected.getTerm());
        assertThat(actual.getId()).isEqualTo(expected.getId());
    }

    @Test
    void whenGetLoan_andNoFound_thenThrowNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> loanService.getLoan(2L));
    }

    @Test
    @Transactional
    void whenGetLoanByAccountId_thenReturnLoan() {
        Account account = accountRepository.save(new Account("user", "pass", UserRole.USER));

        Loan expected = mockedLoan();
        expected.setId(1L);
        expected.setAccount(account);
        expected = loanRepository.save(expected);

        Loan actual = loanService.getLoanByAccountId(account.getId(), 1L);

        assertEquals(0, actual.getAmount().compareTo(expected.getAmount()));
        assertEquals(0, actual.getInterestRate().compareTo(expected.getInterestRate()));
        assertThat(actual.getTerm()).isEqualTo(expected.getTerm());
        assertThat(actual.getId()).isEqualTo(expected.getId());
    }

    @Test
    @Transactional
    void whenGetLoanByAccountId_andNoFound_thenThrowNotFound() {
        Account account = accountRepository.save(new Account("user", "pass", UserRole.USER));

        Assertions.assertThrows(NotFoundException.class, () -> loanService.getLoanByAccountId(account.getId(), 1L));
    }
}
