package com.turkcell.crm.catalogService.business.dtos.request.catalogProperties;

import com.turkcell.crm.catalogService.entity.Catalog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCatalogPropertyRequest {
    private int catalogId;
    private String key;
    private String value;
}
