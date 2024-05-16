package com.turkcell.crm.catalogService.business.dtos.response.catalogProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedCatalogPropertyResponse {
    private int id;
    private int catalogId;
    private Map<String, String> properties;
}
