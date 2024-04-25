package com.turkcell.turkcellcrm.customerService.business.dtos.response.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedAccountResponse {
    private int id;
    private String accountNumber;
    private String accountName;
    private int accountTypeId;
    private int customerId;
    private int addressId;
}
