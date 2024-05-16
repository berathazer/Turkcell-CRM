package com.turkcell.crm.catalogService.business.dtos.request.catalogProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCatalogPropertyRequest {
    private int id;
    private int catalogId;
    private String key;
    private String value;
}
