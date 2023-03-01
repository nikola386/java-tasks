package com.nikola.LoansApi.utils;

import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.Payment;

import java.math.BigDecimal;
import java.util.List;

public class Mocks {
    public static Loan mockedLoan() {
        Loan loan = new Loan(BigDecimal.valueOf(1), 1, BigDecimal.valueOf(1));
        loan.setPayments(List.of(new Payment(), new Payment()));
        return loan;
    }
}
