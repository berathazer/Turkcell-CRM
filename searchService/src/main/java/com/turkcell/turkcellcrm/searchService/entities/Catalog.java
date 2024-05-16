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
@Document(collection = "catalogs")
public class Catalog {

    @Id
    private ObjectId id;

    @Field(name = "catalogId")
    private int catalogId;

    @Field(name = "name")
    private String name;

}
