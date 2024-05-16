package com.turkcell.turkcellcrm.common.events.customer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreatedEvent {
    private String nationalityNumber;
    private int customerId;
    private String accountAccountNumber;
    private String mobilePhoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
}
