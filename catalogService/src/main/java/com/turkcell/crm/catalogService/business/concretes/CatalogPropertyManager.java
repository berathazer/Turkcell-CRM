package com.turkcell.crm.catalogService.business.concretes;

import com.turkcell.crm.catalogService.business.abstracts.CatalogPropertyService;
import com.turkcell.crm.catalogService.business.dtos.request.catalogProperties.CreateCatalogPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.request.catalogProperties.UpdateCatalogPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.CreatedCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.GetAllCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.GetByIdCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.UpdatedCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.rules.CatalogPropertyBusinessRules;
import com.turkcell.crm.catalogService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.catalogService.dataAccess.CatalogPropertyRepository;
import com.turkcell.crm.catalogService.dataAccess.CatalogRepository;
import com.turkcell.crm.catalogService.entity.Catalog;
import com.turkcell.crm.catalogService.entity.CatalogProperty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogPropertyManager implements CatalogPropertyService {

    private ModelMapperService modelMapperService;
    private CatalogPropertyRepository catalogPropertyRepository;
    private CatalogPropertyBusinessRules catalogPropertyBusinessRules;

    @Override
    public CreatedCatalogPropertyResponse add(CreateCatalogPropertyRequest createCatalogPropertyRequest) {

        this.catalogPropertyBusinessRules.isCatalogExistByCatalogId(createCatalogPropertyRequest.getCatalogId());

        CatalogProperty catalogProperty = this.modelMapperService.forRequest().map(createCatalogPropertyRequest, CatalogProperty.class);
        catalogProperty.setId(0);
        CatalogProperty saveCatalog = this.catalogPropertyRepository.save(catalogProperty);


        CreatedCatalogPropertyResponse createdCatalogPropertyResponse = this.modelMapperService.forResponse().map(saveCatalog, CreatedCatalogPropertyResponse.class);
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

        this.catalogPropertyBusinessRules.isCatalogExistByCatalogId(updateCatalogPropertyRequest.getCatalogId());
        this.catalogPropertyBusinessRules.isCatalogPropertyExistById(updateCatalogPropertyRequest.getId());

        CatalogProperty catalogProperty = this.modelMapperService.forRequest().map(updateCatalogPropertyRequest, CatalogProperty.class);
        return this.modelMapperService.forResponse().
                map(this.catalogPropertyRepository.save(catalogProperty), UpdatedCatalogPropertyResponse.class);
    }

    @Override
    public GetByIdCatalogPropertyResponse getById(int id) {

        this.catalogPropertyBusinessRules.isCatalogPropertyExistById(id);

        Optional<CatalogProperty> catalogProperty = this.catalogPropertyRepository.findById(id);

        return this.modelMapperService.forResponse().map(catalogProperty.get(), GetByIdCatalogPropertyResponse.class);
    }

    @Override
    public void delete(int id) {

        CatalogProperty catalogProperty = this.catalogPropertyBusinessRules.isCatalogPropertyAlreadyDeleted(id);
        catalogProperty.setDeletedDate(LocalDateTime.now());

        //this.customerProducer.sendDeletedMessage(id);

        this.catalogPropertyRepository.save(catalogProperty);
    }

}
