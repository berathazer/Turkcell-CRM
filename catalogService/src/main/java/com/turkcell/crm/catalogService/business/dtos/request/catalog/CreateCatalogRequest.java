package com.turkcell.crm.catalogService.business.dtos.request.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCatalogRequest {

    private String name;
    private String description;
    private double price;
    private int unitInStock;

}
