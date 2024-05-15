package com.turkcell.crm.catalogService.business.dtos.response.catalogProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdCatalogPropertyResponse {
    private int id;
    private int catalogId;
    private String catalogProperty;
    private String catalogPropertyDetail;
}
