package com.turkcell.crm.catalogService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkcell.crm.catalogService.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "catalog_properties")
public class CatalogProperty extends BaseEntity {

    private String key;
    private String value;



    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

}
