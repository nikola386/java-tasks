package com.nikola.LoansApi.services;

import com.nikola.LoansApi.models.Account;

public interface AccountService {
    void initialize();
    Account getById(Long id);
}
