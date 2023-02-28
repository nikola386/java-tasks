package com.nikola.LoansApi.services;

import com.nikola.LoansApi.models.Account;

public interface AccountService {
    Account getById(Long id);
}
