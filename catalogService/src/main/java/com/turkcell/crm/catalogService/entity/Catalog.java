package com.turkcell.crm.catalogService.entity;

import com.turkcell.crm.catalogService.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="catalogs")
public class Catalog extends BaseEntity {

    private String name;
    private String description;
    private double price;
    private int unitInStock;

    @OneToMany(mappedBy = "catalog")
    private List<CatalogProperty> properties = new ArrayList<>();

}
