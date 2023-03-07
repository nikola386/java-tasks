package com.nikola.LoansApi.controllers;

import com.nikola.LoansApi.models.CustomUserDetails;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.dto.LoanRequest;
import com.nikola.LoansApi.models.Payment;
import com.nikola.LoansApi.services.LoanService;
import com.nikola.LoansApi.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@Tag(name = "User")
@SecurityRequirement(name = "basicAuth")
public class UserController {
    private final LoanService loanService;
    private final PaymentService paymentService;

    @Autowired
    public UserController(LoanService loanService, PaymentService paymentService) {
        this.loanService = loanService;
        this.paymentService = paymentService;
    }

    @Operation(summary = "Get all loans", description = "Get all loans user has.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan requested", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Loan.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content)})
    @GetMapping(value = "/loans")
    public ResponseEntity<List<Loan>> getLoans(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        List<Loan> loans = loanService.getLoans(principal.getId());
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @Operation(summary = "Request new loan", description = "Request new loan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan requested", content = {
                    @Content(schema = @Schema(implementation = Loan.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content)})
    @PostMapping(value = "/loans")
    public ResponseEntity<Loan> getLoan(Authentication authentication, @Valid @RequestBody LoanRequest request) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        Loan loan = loanService.createLoan(principal.getId(), request);
        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }

    @Operation(summary = "Get loan schedule", description = "Get loan schedule for given loanId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan schedule found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Payment.class)))}),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @GetMapping(value = "/loans/{loanId}/schedule")
    public ResponseEntity<List<Payment>> getLoanSchedule(Authentication authentication,
                                                         @NotNull @PathVariable("loanId") Long loanId) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        Loan loan = loanService.getLoanByAccountId(principal.getId());
        return new ResponseEntity<>(loan.getPayments(), HttpStatus.OK);
    }

    @Operation(summary = "Make payment for any loan", description = "Make payment for any loan by given loanId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan payment made", content = {
                    @Content(schema = @Schema(implementation = Payment.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @PatchMapping(value = "/loans/{loanId}/payments/{paymentId}")
    public ResponseEntity<Payment> makePayment(Authentication authentication,
                                               @NotNull @PathVariable("loanId") Long loanId,
                                               @NotNull @PathVariable("paymentId") Long paymentId) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        Payment payment = paymentService.makePayment(paymentId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
