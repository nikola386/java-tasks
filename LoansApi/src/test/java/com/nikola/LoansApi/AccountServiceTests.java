package com.nikola.LoansApi;

import com.nikola.LoansApi.enums.UserRole;
import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.repositories.AccountRepository;
import com.nikola.LoansApi.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests
{
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountService accountService;

    @Test
    void whenInitialize_thenSaveTwoUsers() {
        Account user = new Account("user", "pass", UserRole.USER);
        Account admin = new Account("admin", "pass", UserRole.ADMIN);
        when(accountRepository.findByUsername(any(String.class))).thenReturn(null);
        when(accountRepository.save(any(Account.class))).thenReturn(user).thenReturn(admin);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encrypted_pass").thenReturn("encrypted_pass");

        accountService.initialize();

        verify(accountRepository, times(2)).save(any(Account.class));
        verify(accountRepository, times(2)).findByUsername(any(String.class));
        verify(passwordEncoder, times(2)).encode(any(String.class));
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    void whenGetById_thenReturnAccount() {
        Account user = new Account("user", "pass", UserRole.USER);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(user));

        Account actual = accountService.getById(1L);

        verify(accountRepository, times(1)).findById(1L);
        assertThat(actual).usingRecursiveComparison().isEqualTo(user);
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    void whenGetById_andNoUser_thenThrowNotFound() {
        when(accountRepository.findById(1L)).thenThrow(NotFoundException.class);

        Assertions.assertThrows(NotFoundException.class, () -> accountService.getById(1L));
        verify(accountRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(accountRepository);
    }
}
