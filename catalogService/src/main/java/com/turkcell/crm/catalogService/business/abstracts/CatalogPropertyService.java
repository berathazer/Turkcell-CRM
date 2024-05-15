package com.turkcell.crm.catalogService.business.abstracts;

import com.turkcell.crm.catalogService.business.dtos.request.catalogProperties.CreateCatalogPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.request.catalogProperties.UpdateCatalogPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.CreatedCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.GetAllCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.GetByIdCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.UpdatedCatalogPropertyResponse;

import java.util.List;

public interface CatalogPropertyService {
    CreatedCatalogPropertyResponse add(CreateCatalogPropertyRequest createCatalogPropertyRequest);
    List<GetAllCatalogPropertyResponse> getAll();
    UpdatedCatalogPropertyResponse update(UpdateCatalogPropertyRequest updateCatalogPropertyRequest);
    GetByIdCatalogPropertyResponse getById(int id);
    void delete(int id);
}
