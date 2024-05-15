package com.turkcell.crm.catalogService.business.dtos.request.catalogProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCatalogPropertyRequest {

    private int catalogId;
    private String catalogProperty;
    private String catalogPropertyDetail;
}
