package com.nikola.LoansApi.services;

import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.models.CustomUserDetails;
import com.nikola.LoansApi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetails implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public AppUserDetails(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account acc = accountRepository.findByUsername(userName).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s not found", userName)));
        return new CustomUserDetails(acc);
    }
}
