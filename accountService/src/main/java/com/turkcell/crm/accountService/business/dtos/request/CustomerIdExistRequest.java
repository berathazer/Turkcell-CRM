package com.turkcell.crm.accountService.business.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerIdExistRequest {
    private int customerId;
}
