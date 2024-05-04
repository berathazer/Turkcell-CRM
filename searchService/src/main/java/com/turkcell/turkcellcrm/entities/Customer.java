package com.turkcell.turkcellcrm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "customers")
// TODO: customer servicedeki tabloları joinle gerekli bilgileri buraya ekle
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "id")
    private int id;
    private String nationalityNumber;
    private String accountAccountNumber;
    private String mobilePhoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
}
