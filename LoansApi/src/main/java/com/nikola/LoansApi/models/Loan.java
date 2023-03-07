package com.nikola.LoansApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikola.LoansApi.enums.LoanStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private int term;
    private BigDecimal interestRate;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName = "id")
    @JsonIgnore
    private Account account;

    public Loan() {
        // default constructor
    }

    public Loan(BigDecimal amount, int term, BigDecimal interestRate) {
        this.id = 0L;
        this.amount = amount;
        this.term = term;
        this.interestRate = interestRate;
        this.status = LoanStatus.APPROVED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}