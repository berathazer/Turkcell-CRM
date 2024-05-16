package com.turkcell.crm.catalogService.business.dtos.response.catalogProperties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCatalogPropertyResponse {
    private int id;
    private int catalogId;
    private String key;
    private String value;
}
