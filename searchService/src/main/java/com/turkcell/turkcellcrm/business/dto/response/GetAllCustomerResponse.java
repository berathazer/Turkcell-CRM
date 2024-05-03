package com.turkcell.turkcellcrm.business.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllCustomerResponse {
    private int id;
    private String nationalityNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    //Role eklenicek
}
