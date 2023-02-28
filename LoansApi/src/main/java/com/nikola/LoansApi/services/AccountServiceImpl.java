package com.nikola.LoansApi.services;

import com.nikola.LoansApi.enums.UserRole;
import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.repositories.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initialize(){
        if(accountRepository.findOneByUsername("admin") == null){
            save(new Account("admin", "admin_pass", UserRole.ADMIN));
        }
        if(accountRepository.findOneByUsername("user") == null){
            save(new Account("user", "user_pass", UserRole.USER));
        }
    }

    public Account getById(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account " + id + " not found"));
    }

    @Transactional
    private Account save(Account user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return accountRepository.save(user);
    }
}
