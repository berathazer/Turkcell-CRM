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

/*
  Catalog sadece genel olarak gruplayıcı başlık (ELEKTRONIK vb. gibi)

  Catalog'un içinde sadece name verebiliriz.

  Şu anki Catalog entitysi Product Olucak ve içine ekstra olarak catalogId alıcak.

  Catalog Property -> ProductProperty olucak ve productId tutucak içinde


  Catalog ve Product ilişkisi =>  1 - N   (İleride N-N yapılabilir ilişki)

  Product ve ProductProperty => 1 - N

 */


public class Product extends BaseEntity {

    private String name;
    private String description;
    private double price;
    private int unitInStock;
    //private int catalogId;
    @OneToMany(mappedBy = "catalog")
    private List<ProductProperty> properties = new ArrayList<>();

}


//public class Product extends BaseEntity {
//
//    private String name;
//    private String description;
//    private double price;
//    private int unitInStock;
//
//    private int catalogId;
//    @OneToMany(mappedBy = "catalog")
//    private List<CatalogProperty> properties = new ArrayList<>();
//
//}

