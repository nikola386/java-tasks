package com.nikola.LoansApi.models.dto;

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

    public LoanRequest() {
    }

    public LoanRequest(BigDecimal amount, int term) {
        this.amount = amount;
        this.term = term;
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
}
