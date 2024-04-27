package com.turkcell.turkcellcrm.entities;

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
    private int id;
    @Field(name = "email")
    private String email;
    @Field(name = "mobilePhoneNumber")
    private String mobilePhoneNumber;

}
