package com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.individualCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllIndividualCustomerResponse {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String fatherName;
    private String motherName;
    private String nationalityId;
    private String email;
    private String phoneNumber;
}
