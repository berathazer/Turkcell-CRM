package com.turkcell.turkcellcrm.business.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCustomerRequest {
    private String nationalityNumber;
    private int id;
    private String accountAccountNumber;
    private String mobilePhoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
}
