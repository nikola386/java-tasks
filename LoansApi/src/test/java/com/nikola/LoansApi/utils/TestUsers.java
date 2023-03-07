package com.nikola.LoansApi.utils;

import com.nikola.LoansApi.enums.UserRole;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.models.CustomUserDetails;
import com.nikola.LoansApi.models.Loan;

import java.util.List;

public class TestUsers {
    public static CustomUserDetails testUser() {
        Account acc = new Account("test_user", "pass", UserRole.USER);
        acc.setId(1L);
        return new CustomUserDetails(acc);
    }

    public static CustomUserDetails testUserWithLoan() {
        Account acc = new Account("test_user", "pass", UserRole.USER, List.of(new Loan()));
        acc.setId(2L);
        return new CustomUserDetails(acc);
    }

    public static CustomUserDetails testAdmin() {
        Account acc = new Account("test_admin", "pass", UserRole.ADMIN);
        acc.setId(3L);
        return new CustomUserDetails(acc);
    }
}
