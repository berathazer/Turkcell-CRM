package com.turkcell.crm.catalogService.business.dtos.response.catalogProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCatalogPropertyResponse {

    private String catalogName;
  //  private String catalogPropertyName;
  //  private String catalogPropertyDetail;

    private List<CatalogPropertyResponse> properties;

}
