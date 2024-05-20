package com.turkcell.turkcellcrm.searchService.business.concretes;

import com.turkcell.turkcellcrm.common.events.catalog.CatalogCreatedEvent;
import com.turkcell.turkcellcrm.common.events.catalog.CatalogUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchCatalogService;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCatalogRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCatalogResponse;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllProductResponse;
import com.turkcell.turkcellcrm.searchService.business.rules.CatalogFilterBusinessRules;
import com.turkcell.turkcellcrm.searchService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchCatalogRepository;
import com.turkcell.turkcellcrm.searchService.entities.Catalog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchCatalogManager implements SearchCatalogService {

    private SearchCatalogRepository searchCatalogRepository;
    private ModelMapperService modelMapperService;
    private CatalogFilterBusinessRules catalogFilterBusinessRules;

    @Override
    public void add(CatalogCreatedEvent catalogCreatedEvent) {

        this.catalogFilterBusinessRules.IsCatalogIdExistById(catalogCreatedEvent);

        Catalog catalog = this.modelMapperService.forRequest().map(catalogCreatedEvent, Catalog.class);
        catalog.setId(null);

        this.searchCatalogRepository.save(catalog);
    }

    @Override
    public List<GetAllProductResponse> getFilteredCatalog(GetAllCatalogRequest getAllCatalogRequest) {
        /*
        Kategoriye ait tüm ürünleri getirecek
        product_id, product_name alanlarına görede filtreleme olabilecek.
        */

        return List.of();
    }

    @Override
    public List<GetAllCatalogResponse> getAllCatalog() {

        List<Catalog> catalogList = this.searchCatalogRepository.findAll();

        return catalogList.stream().
                map(catalog -> this.modelMapperService.forResponse().
                        map(catalog, GetAllCatalogResponse.class)).toList();
    }

    @Override
    public void update(CatalogUpdatedEvent catalogUpdatedEvent) {

        this.catalogFilterBusinessRules.IsCatalogIdExistById(catalogUpdatedEvent);

        Catalog catalog = this.modelMapperService.forRequest().map(catalogUpdatedEvent, Catalog.class);

        this.searchCatalogRepository.save(catalog);
    }

    @Override
    public void delete(int catalogId) {

        Catalog catalog = this.catalogFilterBusinessRules.IsCatalogAlreadyDeleted(catalogId);
        catalog.setDeletedDate(LocalDateTime.now());

        this.searchCatalogRepository.save(catalog);
    }
}
