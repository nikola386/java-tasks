package com.nikola.LoansApi;

import com.nikola.LoansApi.enums.UserRole;
import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountServiceTests
{
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void whenInitialize_thenSaveTwoUsers() {
        Account user = new Account("user", "pass", UserRole.USER);
        Account admin = new Account("admin", "pass", UserRole.ADMIN);

        accountService.initialize();
        Account actualUser = accountService.getById(2L);
        Account actualAdmin = accountService.getById(1L);

        assertThat(actualUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(actualAdmin.getUsername()).isEqualTo(admin.getUsername());
    }

    @Test
    void whenGetById_andNoUser_thenThrowNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> accountService.getById(123L));
    }
}
