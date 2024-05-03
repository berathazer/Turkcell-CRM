package com.turkcell.turkcellcrm.kafka.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateCustomerEvent {
    private String nationalityNumber;
    private int id;
    private String accountAccountNumber;
    private String mobilePhoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
}
