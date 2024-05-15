package com.turkcell.crm.catalogService.business.dtos.request.catalog;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCatalogRequest {

    private int id;
    private String name;
    private String description;
    private double price;
    private int unitInStock;

}
