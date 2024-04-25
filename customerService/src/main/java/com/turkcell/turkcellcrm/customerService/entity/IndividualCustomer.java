package com.turkcell.turkcellcrm.customerService.entity;

import com.turkcell.turkcellcrm.customerService.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "individual_customers")
public class IndividualCustomer extends Customer {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String fatherName;
    private String motherName;
    private String nationalityNumber;
    private String homePhoneNumber;
    private Gender gender;

    @Column(name = "individual_id", insertable = false, updatable = false)
    private Integer individualId;
}
