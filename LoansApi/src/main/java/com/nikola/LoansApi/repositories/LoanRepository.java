package com.nikola.LoansApi.repositories;

import com.nikola.LoansApi.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByAccountId(Long accountId);
}
