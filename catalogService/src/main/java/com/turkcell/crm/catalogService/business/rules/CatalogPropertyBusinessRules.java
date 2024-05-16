package com.turkcell.crm.catalogService.business.rules;

import com.turkcell.crm.catalogService.business.abstracts.CatalogPropertyService;
import com.turkcell.crm.catalogService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.catalogService.dataAccess.CatalogPropertyRepository;
import com.turkcell.crm.catalogService.entity.Catalog;
import com.turkcell.crm.catalogService.entity.CatalogProperty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogPropertyBusinessRules {
    private CatalogPropertyRepository catalogPropertyRepository;
    private CatalogBusinessRules catalogBusinessRules;

    public CatalogProperty isCatalogPropertyExistById(int id){

        Optional<CatalogProperty> catalogProperty = this.catalogPropertyRepository.findById(id);

        if (catalogProperty.isEmpty()){
            throw new BusinessException("Catalog Property not found");
        }

        return catalogProperty.get();
    }

    public void isCatalogExistByCatalogId(int catalogId){

        this.catalogBusinessRules.isCatalogExistById(catalogId);
    }

    public CatalogProperty isCatalogPropertyAlreadyDeleted(int id){
        CatalogProperty catalogProperty = this.isCatalogPropertyExistById(id);

        if(catalogProperty.getDeletedDate() != null) {
            throw new BusinessException("Catalog property already deleted");
        }

        return catalogProperty;

    }
}
