package com.turkcell.turkcellcrm.customerService.business.dtos.response.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreatedAccountResponse {
    private int id;
    private String accountNumber;
    private String accountName;
    private int accountTypeId;
    private int customerId;
    private int addressId;
}
