package com.turkcell.turkcellcrm.customerService.business.dtos.request.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateAccountRequest {
    @NotNull
    @Size(min = 16,max = 16)
    private String accountNumber;
    @NotNull
    private String accountName;
    @NotNull
    private int accountTypeId;
    @NotNull
    private int customerId;
    @NotNull
    private int addressId;
}
