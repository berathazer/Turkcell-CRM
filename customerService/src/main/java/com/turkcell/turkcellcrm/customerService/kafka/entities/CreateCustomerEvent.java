package com.turkcell.turkcellcrm.customerService.kafka.entities;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateCustomerEvent {
    //private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String fatherName;
    private String motherName;
    private String nationalityNumber;
    private String email;
    private String phoneNumber;
}
