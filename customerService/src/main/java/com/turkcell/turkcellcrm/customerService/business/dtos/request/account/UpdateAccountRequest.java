package com.turkcell.turkcellcrm.customerService.business.dtos.request.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest {
    @NotNull
    private int id;
    @NotNull
    @Size(min = 19,max = 19)
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
