package com.nikola.LoansApi.controllers;

import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.Payment;
import com.nikola.LoansApi.services.LoanService;
import com.nikola.LoansApi.services.PaymentService;
import com.nikola.LoansApi.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Admin")
@SecurityRequirement(name = "basicAuth")
public class AdminController {
    private final LoanService loanService;
    private final PaymentService paymentService;

    @Autowired
    public AdminController(LoanService loanService, PaymentService paymentService) {
        this.loanService = loanService;
        this.paymentService = paymentService;
    }

    @Operation(summary = "Get loan schedule", description = "Get loan schedule for given loanId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan schedule found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Payment.class)))}),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @GetMapping(value = "/loan/{loanId}/schedule")
    public List<Payment> getLoanSchedule(@PathVariable("loanId") Long loanId) {
        Loan loan = loanService.getLoan(loanId);
        return loan.getPayments();
    }

    @Operation(summary = "Make payment for any loan", description = "Make payment for any loan by given loanId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan payment made"),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @PatchMapping(value = "/loan/{loanId}/payment")
    public void makePayment(@PathVariable("loanId") Long loanId) {
        Loan loan = loanService.getLoan(loanId);
        paymentService.makePayment(loan);
    }

    @Operation(summary = "Forgive payment for any loan", description = "Forgive payment for any loan by given loanId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan payment forgiven"),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @DeleteMapping(value = "/loan/{loanId}/payment")
    public void cancelPayment(@PathVariable("loanId") Long loanId) {
        Loan loan = loanService.getLoan(loanId);
        paymentService.forgivePayment(loan);
    }
}
