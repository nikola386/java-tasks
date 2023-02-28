package com.nikola.LoansApi.services;

import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.Payment;
import com.nikola.LoansApi.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final PaymentService paymentService;
    private final AccountService accountService;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, PaymentService paymentService, AccountService accountService) {
        this.loanRepository = loanRepository;
        this.paymentService = paymentService;
        this.accountService = accountService;
    }

    public Loan createLoan(Long userId, BigDecimal amount, int term, BigDecimal interestRate) {
        Loan loan = new Loan(amount, term, interestRate);

        Account acc = accountService.getById(userId);
        if(acc.getLoan() != null) {
            throw new RuntimeException("User already has a loan");
        }
        loan.setAccount(acc);

        loan = loanRepository.save(loan);
        List<Payment> payments = paymentService.createPayments(loan);
        loan.setPayments(payments);

        return loan;
    }

    public Loan getLoan(Long loanId) {
        return loanRepository.findById(loanId).orElseThrow(() -> new NotFoundException("Loan " + loanId + " not found"));
    }

    public Loan getLoanByAccountId(Long accountId) {
        return loanRepository.findByAccountId(accountId).orElseThrow(() -> new NotFoundException("Loan for account " + accountId + " not found"));
    }
}
