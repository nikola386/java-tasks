package com.nikola.LoansApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikola.LoansApi.enums.PaymentStatus;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Relation(collectionRelation = "payments", itemRelation = "payment")
public class Payment extends RepresentationModel<Payment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dueDate;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @ManyToOne
    @JoinColumn(name = "loan_id")
    @JsonIgnore
    private Loan loan;

    public Payment() {
        this.status = PaymentStatus.UPCOMING;
    }

    public Payment(LocalDate dueDate, BigDecimal amount, Loan loan) {
        this.id = 0L;
        this.dueDate = dueDate;
        this.amount = amount;
        this.status = PaymentStatus.UPCOMING;
        this.loan = loan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
