package com.turkcell.turkcellcrm.searchService.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "customers")
public class Customer {

    @Id
    private ObjectId id;

    @Field(name = "customerId")
    private int customerId;

    @Field(name = "nationalityNumber")
    private String nationalityNumber;

    @Field(name = "accountAccountNumber")
    private String accountAccountNumber;

    @Field(name = "mobilePhoneNumber")
    private String mobilePhoneNumber;

    @Field(name = "firstName")
    private String firstName;

    @Field(name = "middleName")
    private String middleName;

    @Field(name = "lastName")
    private String lastName;
}
