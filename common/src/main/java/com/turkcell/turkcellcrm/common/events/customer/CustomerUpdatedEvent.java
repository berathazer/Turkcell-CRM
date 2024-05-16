package com.turkcell.turkcellcrm.common.events.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdatedEvent {
    private String nationalityNumber;
    private int id;
    private String accountAccountNumber;
    private String mobilePhoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
}
