package com.nikola.LoansApi;

import com.nikola.LoansApi.enums.PaymentStatus;
import com.nikola.LoansApi.exceptions.NotFoundException;
import com.nikola.LoansApi.models.Loan;
import com.nikola.LoansApi.models.LoanRequest;
import com.nikola.LoansApi.services.LoanService;
import com.nikola.LoansApi.utils.TestUsers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.nikola.LoansApi.utils.Json.asJsonString;
import static com.nikola.LoansApi.utils.Mocks.mockedLoan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class UserApiTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService mockLoanService;

    @Test
    public void whenNoUser_thenReturnUnauthorized() throws Exception {
        this.mockMvc.perform(post("/api/user/loan")
                        .with(csrf())
                        .content(asJsonString(new LoanRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void whenUserHasNoPermissions_thenReturnForbidden() throws Exception {
        this.mockMvc.perform(post("/api/user/loan")
                        .with(csrf())
                        .with(user(TestUsers.testAdmin()))
                        .content(asJsonString(new LoanRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenUserHasLoan_thenReturnBadRequest() throws Exception {
        when(this.mockLoanService.createLoan(eq(2L), any())).thenThrow(RuntimeException.class);

        this.mockMvc.perform(post("/api/user/loan")
                        .with(csrf())
                        .with(user(TestUsers.testUserWithLoan()))
                        .content(asJsonString(new LoanRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostRequestLoan_thenReturnStatusCreatedAndLoan() throws Exception {
        when(this.mockLoanService.createLoan(eq(1L), any())).thenReturn(mockedLoan());

        this.mockMvc.perform(post("/api/user/loan")
                        .with(csrf())
                        .with(user(TestUsers.testUser()))
                        .content(asJsonString(new LoanRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(1))
                .andExpect(jsonPath("$.term").value(1))
                .andExpect(jsonPath("$.interestRate").value(1))
                .andExpect(jsonPath("$.payments", hasSize(2)));
    }

    @Test
    public void whenGetSchedule_thenReturnLoanPayments() throws Exception {
        when(this.mockLoanService.getLoanByAccountId(1L)).thenReturn(mockedLoan());

        this.mockMvc.perform(get("/api/user/loan/schedule")
                        .with(csrf())
                        .with(user(TestUsers.testUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void whenGetScheduleUserHasNoLoan_thenReturnNotFound() throws Exception {
        when(this.mockLoanService.getLoanByAccountId(1L)).thenThrow(NotFoundException.class);

        this.mockMvc.perform(get("/api/user/loan/schedule")
                        .with(csrf())
                        .with(user(TestUsers.testUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenPatchPayment_thenReturnOk() throws Exception {
        Loan loan = mockedLoan();
        loan.getPayments().get(0).setStatus(PaymentStatus.PAID);
        when(this.mockLoanService.getLoanByAccountId(1L)).thenReturn(loan);

        this.mockMvc.perform(patch("/api/user/loan/payment")
                        .with(csrf())
                        .with(user(TestUsers.testUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPatchPaymentUserHasNoLoan_thenReturnNotFound() throws Exception {
        when(this.mockLoanService.getLoanByAccountId(1L)).thenThrow(NotFoundException.class);

        this.mockMvc.perform(patch("/api/user/loan/payment")
                        .with(csrf())
                        .with(user(TestUsers.testUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenPatchPaymentLoanAlreadyPaid_thenReturnBadRequest() throws Exception {
        when(this.mockLoanService.getLoanByAccountId(1L)).thenThrow(RuntimeException.class);

        this.mockMvc.perform(patch("/api/user/loan/payment")
                        .with(csrf())
                        .with(user(TestUsers.testUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
