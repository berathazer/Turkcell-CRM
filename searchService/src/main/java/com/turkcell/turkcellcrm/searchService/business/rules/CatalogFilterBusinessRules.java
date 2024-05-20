package com.turkcell.turkcellcrm.searchService.business.rules;

import com.turkcell.turkcellcrm.common.events.catalog.CatalogCreatedEvent;
import com.turkcell.turkcellcrm.common.events.catalog.CatalogUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.messages.CatalogFilterBusinessRulesMessages;
import com.turkcell.turkcellcrm.searchService.business.messages.CustomerFilterBusinessRulesMessages;
import com.turkcell.turkcellcrm.searchService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchCatalogRepository;
import com.turkcell.turkcellcrm.searchService.entities.Catalog;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CatalogFilterBusinessRules {

    private SearchCatalogRepository searchCatalogRepository;
    private MongoTemplate mongoTemplate;

    public void IsCatalogIdExistById(CatalogCreatedEvent catalogCreatedEvent){

        if (this.searchCatalogRepository.findCatalogByCatalogId(catalogCreatedEvent.getId()).isPresent()){
            throw new BusinessException(CatalogFilterBusinessRulesMessages.CATALOG_IS_ALREADY_EXISTS);
        }
    }

    public void IsCatalogIdExistById(CatalogUpdatedEvent catalogUpdatedEvent){
        if (this.searchCatalogRepository.findCatalogByCatalogId(catalogUpdatedEvent.getId()).isPresent()){
            throw new BusinessException(CatalogFilterBusinessRulesMessages.CATALOG_IS_ALREADY_EXISTS);
        }
    }

    public Catalog IsCatalogAlreadyDeleted(int catalogId){

        Catalog catalog = this.searchCatalogRepository.findCatalogByCatalogId(catalogId).
                orElseThrow(() -> new BusinessException(CatalogFilterBusinessRulesMessages.CATALOG_NOT_EXISTS));

        if(catalog.getDeletedDate() != null){
            throw new BusinessException(CatalogFilterBusinessRulesMessages.CATALOG_IS_ALREADY_DELETED);
        }

        return catalog;
    }
}
