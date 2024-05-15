package com.turkcell.crm.catalogService.business.dtos.response.catalogProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogPropertyResponse {
    private String propertyName;
    private String propertyValue;
}
