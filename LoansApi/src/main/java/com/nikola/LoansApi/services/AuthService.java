package com.nikola.LoansApi.services;

import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.models.CustomUser;
import com.nikola.LoansApi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public AuthService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CustomUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account u = accountRepository.findOneByUsername(userName);
        CustomUser user = createUser(u);
        return user;
    }

    private CustomUser createUser(Account u) {
        return new CustomUser(u.getId(), u.getUsername(), u.getPassword(), createAuthorities(u));
    }

    private Collection<GrantedAuthority> createAuthorities(Account u) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole().name()));
        return  authorities;
    }
}
