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

import static com.nikola.LoansApi.utils.JsonUtils.asJsonString;
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
class AdminApiTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Test
    public void whenNoAuth_thenReturnUnauthorized() throws Exception {
        this.mockMvc.perform(get("/api/admin/1/schedule")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void whenNoPermissions_thenReturnForbidden() throws Exception {
        this.mockMvc.perform(get("/api/admin/loan/1/schedule")
                        .with(csrf())
                        .with(user(TestUsers.testUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenAdminGetSchedule_andNotFound_thenReturnNotFound() throws Exception {
        when(this.loanService.getLoan(1L)).thenThrow(NotFoundException.class);

        this.mockMvc.perform(get("/api/admin/loan/1/schedule")
                        .with(csrf())
                        .with(user(TestUsers.testAdmin()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenAdminGetSchedule_thenReturnOk() throws Exception {
        when(this.loanService.getLoan(1L)).thenReturn(mockedLoan());

        this.mockMvc.perform(get("/api/admin/loan/1/schedule")
                        .with(csrf())
                        .with(user(TestUsers.testAdmin()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void whenAdminDeletePayment_andNotFound_thenReturnNotFound() throws Exception {
        when(this.loanService.getLoan(1L)).thenThrow(NotFoundException.class);

        this.mockMvc.perform(delete("/api/admin/loan/1/payment")
                        .with(csrf())
                        .with(user(TestUsers.testAdmin()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenAdminDeletePayment_thenReturnOk() throws Exception {
        Loan loan = mockedLoan();
        loan.getPayments().get(0).setStatus(PaymentStatus.FORGIVEN);
        when(this.loanService.getLoan(1L)).thenReturn(loan);

        this.mockMvc.perform(delete("/api/admin/loan/1/payment")
                        .with(csrf())
                        .with(user(TestUsers.testAdmin()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenAdminPatchPayment_andNotFound_thenReturnNotFound() throws Exception {
        when(this.loanService.getLoan(1L)).thenThrow(NotFoundException.class);

        this.mockMvc.perform(patch("/api/admin/loan/1/payment")
                        .with(csrf())
                        .with(user(TestUsers.testAdmin()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenAdminPatchPayment_thenReturnNotFound() throws Exception {
        Loan loan = mockedLoan();
        loan.getPayments().get(0).setStatus(PaymentStatus.PAID);
        when(this.loanService.getLoan(1L)).thenReturn(loan);

        this.mockMvc.perform(delete("/api/admin/loan/1/payment")
                        .with(csrf())
                        .with(user(TestUsers.testAdmin()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
