package com.turkcell.crm.catalogService.business.concretes;

import com.turkcell.crm.catalogService.business.abstracts.CatalogPropertyService;
import com.turkcell.crm.catalogService.business.dtos.request.catalogProperties.CreateCatalogPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.request.catalogProperties.UpdateCatalogPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.CreatedCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.GetAllCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.GetByIdCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.UpdatedCatalogPropertyResponse;
import com.turkcell.crm.catalogService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.catalogService.dataAccess.CatalogPropertyRepository;
import com.turkcell.crm.catalogService.entity.CatalogProperty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CatalogPropertyManager implements CatalogPropertyService {
    private ModelMapperService modelMapperService;
    private CatalogPropertyRepository catalogPropertyRepository;


    @Override
    public CreatedCatalogPropertyResponse add(CreateCatalogPropertyRequest createCatalogPropertyRequest) {


        CatalogProperty catalogProperty  = this.modelMapperService.forRequest().map(createCatalogPropertyRequest, CatalogProperty.class);

        List<Map<String, String>> mappedProperties = mapProperties(createCatalogPropertyRequest.getCatalogProperty(), createCatalogPropertyRequest.getCatalogPropertyDetail());
        catalogProperty.setProperties(mappedProperties);

        CreatedCatalogPropertyResponse createdCatalogPropertyResponse = this.modelMapperService.forResponse().map(this.catalogPropertyRepository.save(catalogProperty), CreatedCatalogPropertyResponse.class);
        return createdCatalogPropertyResponse;
    }

    @Override
    public List<GetAllCatalogPropertyResponse> getAll() {

        List<CatalogProperty> catalogProperties = this.catalogPropertyRepository.findAll();

        List<GetAllCatalogPropertyResponse> getAllCatalogPropertyResponses = catalogProperties.stream().map(catalogProperty -> this.modelMapperService.forResponse().
                map(catalogProperty, GetAllCatalogPropertyResponse.class)).toList();
        return getAllCatalogPropertyResponses;
    }

    @Override
    public UpdatedCatalogPropertyResponse update(UpdateCatalogPropertyRequest updateCatalogPropertyRequest) {

        CatalogProperty catalogProperty = this.modelMapperService.forRequest().map(updateCatalogPropertyRequest,CatalogProperty.class);
        return this.modelMapperService.forResponse().
                map(this.catalogPropertyRepository.save(catalogProperty), UpdatedCatalogPropertyResponse.class);
    }

    @Override
    public GetByIdCatalogPropertyResponse getById(int id) {

        Optional<CatalogProperty> catalogProperty =this.catalogPropertyRepository.findById(id);
        return this.modelMapperService.forResponse().map(catalogProperty.get(), GetByIdCatalogPropertyResponse.class);
    }

    @Override
    public void delete(int id) {
     this.catalogPropertyRepository.getById(id);
    }

    public List<Map<String,String>> mapProperties(String key, String value) {
        Map<String, String> property = new HashMap<>();
        property.put(key, value);
        List<Map<String, String>> properties = new ArrayList<>();
        properties.add(property);
        return properties;
    }
}
