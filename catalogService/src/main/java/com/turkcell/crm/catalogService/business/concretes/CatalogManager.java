package com.turkcell.crm.catalogService.business.concretes;

import com.turkcell.crm.catalogService.business.abstracts.CatalogService;
import com.turkcell.crm.catalogService.business.dtos.request.catalog.CreateCatalogRequest;
import com.turkcell.crm.catalogService.business.dtos.request.catalog.UpdateCatalogRequest;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.CreatedCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.GetAllCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.GetByIdCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.UpdatedCatalogResponse;
import com.turkcell.crm.catalogService.business.rules.CatalogBusinessRules;
import com.turkcell.crm.catalogService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.catalogService.dataAccess.CatalogRepository;
import com.turkcell.crm.catalogService.entity.Catalog;
import com.turkcell.crm.catalogService.entity.Product;
import com.turkcell.turkcellcrm.common.events.product.ProductDeletedEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogManager implements CatalogService {

    private CatalogRepository catalogRepository;
    private ModelMapperService modelMapperService;
    private CatalogBusinessRules catalogBusinessRules;

    @Override
    public CreatedCatalogResponse add(CreateCatalogRequest createCatalogRequest) {

        Catalog catalog = this.modelMapperService.forRequest().map(createCatalogRequest,Catalog.class);

        Catalog savedCatalog = this.catalogRepository.save(catalog);

        return this.modelMapperService.forResponse().map(savedCatalog, CreatedCatalogResponse.class);
    }

    @Override
    public List<GetAllCatalogResponse> getAll() {
        List<Catalog> catalogs = this.catalogRepository.findAll();
        return catalogs.stream().map(catalog -> this.modelMapperService.forResponse()
                .map(catalog,GetAllCatalogResponse.class)).toList();
    }

    @Override
    public UpdatedCatalogResponse update(UpdateCatalogRequest updateCatalogRequest) {

        this.catalogBusinessRules.isCatalogExistById(updateCatalogRequest.getId());

        Catalog catalog = this.modelMapperService.forRequest().map(updateCatalogRequest,Catalog.class);
        catalog.setUpdatedDate(LocalDateTime.now());

        Catalog savedCatalog = this.catalogRepository.save(catalog);

        return this.modelMapperService.forResponse().map(savedCatalog, UpdatedCatalogResponse.class);
    }


    @Override
    public GetByIdCatalogResponse getById(int id) {

        Optional<Catalog> catalog = this.catalogRepository.findById(id);

        return this.modelMapperService.forResponse().map(catalog.get(),GetByIdCatalogResponse.class);
    }

    @Override
    public void delete(int id) {
        Catalog catalog = this.catalogBusinessRules.isCatalogAlreadyDeleted(id);
        catalog.setDeletedDate(LocalDateTime.now());

        this.catalogRepository.save(catalog);

    }
}
