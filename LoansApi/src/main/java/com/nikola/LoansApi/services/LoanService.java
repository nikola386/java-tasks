package com.nikola.LoansApi.services;

import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Account;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.LoanRequest;
import com.nikola.LoansApi.models.Payment;
import com.nikola.LoansApi.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final PaymentService paymentService;
    private final AccountService accountService;

    @Autowired
    public LoanService(LoanRepository loanRepository, PaymentService paymentService, AccountService accountService) {
        this.loanRepository = loanRepository;
        this.paymentService = paymentService;
        this.accountService = accountService;
    }

    public Loan createLoan(Long userId, LoanRequest request) {
        Account acc = accountService.getById(userId);
        if(acc.getLoan() != null) {
            throw new RuntimeException("User already has a loan");
        }

        Loan loan = new Loan(request.getAmount(), request.getTerm(), request.getInterestRate());
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
