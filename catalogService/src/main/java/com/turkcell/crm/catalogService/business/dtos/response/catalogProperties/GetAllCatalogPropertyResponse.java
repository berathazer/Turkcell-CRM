package com.turkcell.crm.catalogService.business.dtos.response.catalogProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCatalogPropertyResponse {
    private int id;

    private String catalogName;
    //  private String catalogPropertyName;
    //  private String catalogPropertyDetail;
    private String key;
    private String value;

    //private Map<String, String> properties;

}
