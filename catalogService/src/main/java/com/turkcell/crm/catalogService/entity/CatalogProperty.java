package com.turkcell.crm.catalogService.entity;

import com.turkcell.crm.catalogService.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="catalog_properties")
public class CatalogProperty extends BaseEntity {

   // private String key;
   // private String value;

    @ElementCollection
    private List<Map<String, String>> properties=new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

}
