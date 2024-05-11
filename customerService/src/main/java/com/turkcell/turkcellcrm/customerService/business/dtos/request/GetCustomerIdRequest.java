package com.turkcell.turkcellcrm.customerService.business.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetCustomerIdRequest {
    private int customerId;
}
