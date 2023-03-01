package com.nikola.LoansApi.services;

import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.LoanRequest;

import java.math.BigDecimal;

public interface LoanService {
    Loan createLoan(Long userId, LoanRequest request);
    Loan getLoan(Long loanId);
    Loan getLoanByAccountId(Long userId);
}
