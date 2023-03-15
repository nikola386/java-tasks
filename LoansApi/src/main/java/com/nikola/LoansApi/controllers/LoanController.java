package com.nikola.LoansApi.controllers;

import com.nikola.LoansApi.exceptions.NotFoundException;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Loan")
@SecurityRequirement(name = "basicAuth")
public class LoanController {
    private final LoanService loanService;
    private final PaymentService paymentService;

    @Autowired
    public LoanController(LoanService loanService, PaymentService paymentService) {
        this.loanService = loanService;
        this.paymentService = paymentService;
    }

    @Operation(summary = "Get mine loans", description = "Get mine loans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan requested", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Loan.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content)})
    @GetMapping(value = "/users/mine/loans")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CollectionModel<Loan>> getLoans(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return getLoans(principal.getId());
    }

    @Operation(summary = "Get all loans for user", description = "Get all loans user has.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan requested", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Loan.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content)})
    @GetMapping(value = "/users/{userId}/loans")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CollectionModel<Loan>> getLoans(@NotNull @PathVariable("userId") Long userId) {
        List<Loan> loans = loanService.getLoansByUserId(userId);

        for (final Loan loan : loans) {
            Link scheduleLink = linkTo(methodOn(LoanController.class)
                    .getLoanSchedule(userId, loan.getId())).withRel("getSchedule");
            loan.add(scheduleLink);

            for (final Payment payment : loan.getPayments()) {
                Link paymentLink = linkTo(methodOn(LoanController.class)
                        .makePayment(userId, loan.getId(), payment.getId())).withRel("makePayment");
                payment.add(paymentLink);
                Link forgiveLink = linkTo(methodOn(LoanController.class)
                        .forgivePayment(userId, loan.getId(), payment.getId())).withRel("forgivePayment");
                payment.add(forgiveLink);
            }
        }
        Link selfLink = linkTo(methodOn(LoanController.class)
                .getLoans(userId)).withSelfRel();

        return new ResponseEntity<>(CollectionModel.of(loans, selfLink), HttpStatus.OK);
    }

    @Operation(summary = "Request new loan", description = "Request new loan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan requested", content = {
                    @Content(schema = @Schema(implementation = Loan.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content)})
    @PostMapping(value = "/users/mine/loans")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Loan> requestLoan(Authentication authentication, @Valid @RequestBody LoanRequest request) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Loan loan = loanService.createLoan(principal.getId(), request);

        for (final Payment payment : loan.getPayments()) {
            Link paymentLink = linkTo(methodOn(LoanController.class)
                    .makePayment(authentication, loan.getId(), payment.getId())).withRel("makePayment");
            payment.add(paymentLink);
        }
        Link allLoansLink = linkTo(methodOn(LoanController.class)
                .getLoans(authentication)).withRel("allLoans");
        loan.add(allLoansLink);

        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }

    @Operation(summary = "Get schedule for my loan", description = "Get schedule for my loan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan schedule found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Payment.class)))}),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @GetMapping(value = "/users/mine/loans/{loanId}/schedule")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CollectionModel<Payment>> getLoanSchedule(Authentication authentication,
                                                         @NotNull @PathVariable("loanId") Long loanId) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return getLoanSchedule(principal.getId(), loanId);
    }

    @Operation(summary = "Get loan schedule for any loan", description = "Get loan schedule for any loan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan schedule found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Payment.class)))}),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @GetMapping(value = "/users/{userId}/loans/{loanId}/schedule")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CollectionModel<Payment>> getLoanSchedule(@NotNull @PathVariable("userId") Long userId,
                                                         @NotNull @PathVariable("loanId") Long loanId) {
        Loan loan = loanService.getLoanByAccountId(userId, loanId);
        List<Payment> payments = loan.getPayments();

        for (final Payment payment : payments) {
            Link paymentLink = linkTo(methodOn(LoanController.class)
                    .makePayment(userId, loan.getId(), payment.getId())).withRel("makePayment");
            payment.add(paymentLink);
        }
        Link selfLink = linkTo(methodOn(LoanController.class)
                .getLoanSchedule(userId, loanId)).withSelfRel();

        return new ResponseEntity<>(CollectionModel.of(payments, selfLink), HttpStatus.OK);
    }

    @Operation(summary = "Make payment for my loan", description = "Make payment for my loan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan payment made", content = {
                    @Content(schema = @Schema(implementation = Payment.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @PatchMapping(value = "/users/mine/loans/{loanId}/payments/{paymentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Payment> makePayment(Authentication authentication,
                                               @NotNull @PathVariable("loanId") Long loanId,
                                               @NotNull @PathVariable("paymentId") Long paymentId) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return makePayment(principal.getId(), loanId, paymentId);
    }

    @Operation(summary = "Make payment for any loan", description = "Make payment for any loan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan payment made", content = {
                    @Content(schema = @Schema(implementation = Payment.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @PatchMapping(value = "/users/{userId}/loans/{loanId}/payments/{paymentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Payment> makePayment(@NotNull @PathVariable("userId") Long userId,
                                               @NotNull @PathVariable("loanId") Long loanId,
                                               @NotNull @PathVariable("paymentId") Long paymentId) {
        Loan loan = loanService.getLoan(loanId);
        if(!userId.equals(loan.getAccount().getId())) {
            throw new NotFoundException("Loan " + loanId + " not found");
        }

        Payment payment = paymentService.makePayment(loan.getId(), paymentId);

        Link scheduleLink = linkTo(methodOn(LoanController.class)
                .getLoanSchedule(userId, loan.getId())).withRel("getSchedule");
        payment.add(scheduleLink);
        Link selfLink = linkTo(methodOn(LoanController.class)
                .makePayment(userId, loan.getId(), paymentId)).withSelfRel();
        payment.add(selfLink);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @Operation(summary = "Forgive payment for any loan", description = "Forgive payment for any loan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan payment forgiven", content = {
                    @Content(schema = @Schema(implementation = Payment.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "User not authorised", content = @Content),
            @ApiResponse(responseCode = "403", description = "User has no permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)})
    @DeleteMapping(value = "/users/{userId}/loans/{loanId}/payments/{paymentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Payment> forgivePayment(@NotNull @PathVariable("userId") Long userId,
                                                  @NotNull @PathVariable("loanId") Long loanId,
                                                  @NotNull @PathVariable("paymentId") Long paymentId) {
        Loan loan = loanService.getLoan(loanId);
        if(!userId.equals(loan.getAccount().getId())) {
            throw new NotFoundException("Loan " + loanId + " not found");
        }

        Payment payment = paymentService.forgivePayment(loan.getId(), paymentId);

        Link scheduleLink = linkTo(methodOn(LoanController.class)
                .getLoanSchedule(userId, loan.getId())).withRel("getSchedule");
        payment.add(scheduleLink);
        Link selfLink = linkTo(methodOn(LoanController.class)
                .makePayment(userId, loan.getId(), paymentId)).withSelfRel();
        payment.add(selfLink);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
