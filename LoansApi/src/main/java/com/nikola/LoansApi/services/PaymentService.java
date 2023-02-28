package com.nikola.LoansApi.services;

import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> createPayments(Loan loan);
    void makePayment(Loan loan);
    void forgivePayment(Loan loan);

}
