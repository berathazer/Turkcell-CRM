package com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.individualCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedIndividualCustomerResponse {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private int gender;
    private String fatherName;
    private String motherName;
    private String nationalityNumber;
    private String email;
    private String phoneNumber;
}
