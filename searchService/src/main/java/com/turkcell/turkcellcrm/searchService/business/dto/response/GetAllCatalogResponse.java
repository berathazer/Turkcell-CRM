package com.turkcell.turkcellcrm.searchService.business.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCatalogResponse {
    private String name;
    private int catalogId;
}
