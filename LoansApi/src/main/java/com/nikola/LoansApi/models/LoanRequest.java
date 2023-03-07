package com.nikola.LoansApi.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class LoanRequest {
    @NotNull
    private BigDecimal amount;

    @NotNull
    @Min(1)
    @Max(120)
    private int term;

    @NotNull
    @Min(1)
    @Max(100)
    private BigDecimal interestRate;

    public LoanRequest() {
    }

    public LoanRequest(BigDecimal amount, int term, BigDecimal interestRate) {
        this.amount = amount;
        this.term = term;
        this.interestRate = interestRate;
    }

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
