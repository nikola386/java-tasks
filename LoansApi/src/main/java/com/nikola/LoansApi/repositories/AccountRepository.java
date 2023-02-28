package com.nikola.LoansApi.repositories;

import com.nikola.LoansApi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findOneByUsername(String username);
}
