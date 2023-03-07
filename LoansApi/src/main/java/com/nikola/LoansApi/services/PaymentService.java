package com.nikola.LoansApi.services;

import com.nikola.LoansApi.enums.PaymentStatus;
import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.Payment;
import com.nikola.LoansApi.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> createPayments(Loan loan) {
        List<Payment> payments = new ArrayList<>();
        BigDecimal monthlyInterestRate = loan.getInterestRate()
                .divide(BigDecimal.valueOf(12.0), 2, RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = loan.getAmount()
                .multiply(monthlyInterestRate)
                .divide(BigDecimal.valueOf(1 -
                        Math.pow(monthlyInterestRate.add(BigDecimal.valueOf(1)).doubleValue(), -loan.getTerm())),
                        2, RoundingMode.HALF_UP);

        LocalDate dueDate = LocalDate.now().plusDays(1);
        for (int i = 1; i <= loan.getTerm(); i++) {
            Payment payment = new Payment(dueDate, monthlyPayment, loan);
            payments.add(payment);
            dueDate = dueDate.plusMonths(1);
        }
        paymentRepository.saveAll(payments);

        return payments;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Payment makePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException("Payment " + paymentId + " not found"));
        payment.setStatus(PaymentStatus.PAID);

        paymentRepository.save(payment);

        return payment;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Payment forgivePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException("Payment " + paymentId + " not found"));

        payment.setStatus(PaymentStatus.FORGIVEN);
        paymentRepository.save(payment);

        return payment;
    }
}
