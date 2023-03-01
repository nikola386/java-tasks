package com.nikola.LoansApi.models;

import java.math.BigDecimal;

public class LoanRequest {
    public LoanRequest() {
    }

    public LoanRequest(BigDecimal amount, int term, BigDecimal interestRate) {
        this.amount = amount;
        this.term = term;
        this.interestRate = interestRate;
    }

    private BigDecimal amount;
    private int term;
    private BigDecimal interestRate;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
