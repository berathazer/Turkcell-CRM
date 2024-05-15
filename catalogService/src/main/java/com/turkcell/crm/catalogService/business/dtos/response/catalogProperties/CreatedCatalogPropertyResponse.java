package com.turkcell.crm.catalogService.business.dtos.response.catalogProperties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCatalogPropertyResponse {
    private int id;
    private int catalogId;
    private String catalogProperty;
    private String catalogPropertyDetail;
}
