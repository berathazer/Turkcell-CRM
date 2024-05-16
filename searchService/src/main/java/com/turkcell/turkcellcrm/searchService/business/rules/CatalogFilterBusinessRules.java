package com.turkcell.turkcellcrm.searchService.business.rules;

import com.turkcell.turkcellcrm.common.events.catalog.CatalogCreatedEvent;
import com.turkcell.turkcellcrm.common.events.catalog.CatalogUpdatedEvent;
import com.turkcell.turkcellcrm.common.events.customer.CustomerCreatedEvent;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCatalogRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.searchService.business.messages.CustomerFilterBusinessRulesMessages;
import com.turkcell.turkcellcrm.searchService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchCatalogRepository;
import com.turkcell.turkcellcrm.searchService.entities.Catalog;
import com.turkcell.turkcellcrm.searchService.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CatalogFilterBusinessRules {
    private SearchCatalogRepository searchCatalogRepository;
    private MongoTemplate mongoTemplate;

    public void IsCatalogIdExistById(CatalogCreatedEvent catalogCreatedEvent){
        if (this.searchCatalogRepository.findCatalogByCatalogId(catalogCreatedEvent.getCatalogId()).isPresent()){
            throw new BusinessException("Catalog is already exists");
        }
    }
    public void IsCatalogIdExistById(CatalogUpdatedEvent catalogUpdatedEvent){
        if (this.searchCatalogRepository.findCatalogByCatalogId(catalogUpdatedEvent.getCatalogId()).isPresent()){
            throw new BusinessException("Catalog is already exists");
        }
    }

    public List<Catalog> filterCatalog(GetAllCatalogRequest getAllCatalogRequest) {

        Query query = new Query();
        Map<String,String> dtoMap = new HashMap();
        dtoMap.put("name" ,getAllCatalogRequest.getName());

        for(Map.Entry<String,String> entry : dtoMap.entrySet()){
            String key= entry.getKey();
            String value = entry.getValue();
            if(!value.equals("string")){  //value != null
                System.out.println(key + " " + value);
                query.addCriteria(Criteria.where(key).regex(".*" + value + ".*","i"));
            }
        }
        if (getAllCatalogRequest.getCatalogId() != 0) {
            query.addCriteria(Criteria.where("catalogId").is(getAllCatalogRequest.getCatalogId()));
        }
        return this.mongoTemplate.find(query, Catalog.class);
    }
}
