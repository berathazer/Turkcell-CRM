package com.turkcell.turkcellcrm.customerService.entity;


import com.turkcell.turkcellcrm.customerService.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String fatherName;
    private String motherName;
    private String nationalityNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

    @OneToOne(mappedBy = "customer")
    private Contact contacts;
}
