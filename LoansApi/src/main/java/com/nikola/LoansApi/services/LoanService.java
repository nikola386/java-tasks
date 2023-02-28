package com.nikola.LoansApi.services;

import com.nikola.LoansApi.models.Loan;

import java.math.BigDecimal;

public interface LoanService {
    Loan createLoan(Long userId, BigDecimal amount, int term, BigDecimal interestRate);
    Loan getLoan(Long loanId);
    Loan getLoanByAccountId(Long userId);
}
