package com.turkcell.turkcellcrm.customerService.business.dtos.response.customer;

import com.turkcell.turkcellcrm.customerService.entity.Address;
import com.turkcell.turkcellcrm.customerService.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCustomerResponse {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String fatherName;
    private String motherName;
    private String nationalityId;

}
