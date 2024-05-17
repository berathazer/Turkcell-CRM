package com.turkcell.crm.catalogService.business.rules;

import com.turkcell.crm.catalogService.business.messages.CatalogPropertyMessages;
import com.turkcell.crm.catalogService.core.business.abstracts.MessageService;
import com.turkcell.crm.catalogService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.catalogService.dataAccess.CatalogPropertyRepository;
import com.turkcell.crm.catalogService.entity.CatalogProperty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogPropertyBusinessRules {

    private CatalogPropertyRepository catalogPropertyRepository;
    private MessageService messageService;

    public CatalogProperty isCatalogPropertyExistById(int id){

        Optional<CatalogProperty> catalogProperty = this.catalogPropertyRepository.findById(id);

        if (catalogProperty.isEmpty()){
            throw new BusinessException(messageService.getMessage(CatalogPropertyMessages.CATALOG_PROPERTY_NOT_FOUND));
        }

        return catalogProperty.get();
    }


    public CatalogProperty isCatalogPropertyAlreadyDeleted(int id){

        CatalogProperty catalogProperty = this.isCatalogPropertyExistById(id);

        if(catalogProperty.getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(CatalogPropertyMessages.CATALOG_PROPERTY_ALREADY_DELETED));
        }

        return catalogProperty;

    }
}
