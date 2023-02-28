package com.nikola.LoansApi.repositories;

import com.nikola.LoansApi.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}