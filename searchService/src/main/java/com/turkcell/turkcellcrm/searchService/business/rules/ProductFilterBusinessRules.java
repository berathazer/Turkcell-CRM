package com.turkcell.turkcellcrm.searchService.business.rules;

import com.turkcell.turkcellcrm.common.events.product.ProductCreatedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllProductRequest;
import com.turkcell.turkcellcrm.searchService.business.messages.ProductFilterBusinessRulesMessages;
import com.turkcell.turkcellcrm.searchService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchProductRepository;
import com.turkcell.turkcellcrm.searchService.entities.Product;
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
public class ProductFilterBusinessRules {

    private SearchProductRepository searchProductRepository;
    private MongoTemplate mongoTemplate;


    public void IsProductIdExistById(ProductCreatedEvent productCreatedEvent){

        if (this.searchProductRepository.findProductByProductId(productCreatedEvent.getProductId()).isPresent()){
            throw new BusinessException(ProductFilterBusinessRulesMessages.PRODUCT_IS_ALREADY_EXISTS);
        }
    }

    public void IsProductIdExistById(ProductUpdatedEvent productUpdatedEvent){

        if (this.searchProductRepository.findProductByProductId(productUpdatedEvent.getProductId()).isPresent()){
            throw new BusinessException(ProductFilterBusinessRulesMessages.PRODUCT_IS_ALREADY_EXISTS);
        }
    }

    public List<Product> filterProduct(GetAllProductRequest getAllProductRequest) {

        Query query = new Query();
        Map<String,String> dtoMap = new HashMap();
        dtoMap.put("name" , getAllProductRequest.getName());

        for(Map.Entry<String,String> entry : dtoMap.entrySet()){

            String key= entry.getKey();
            String value = entry.getValue();

            if(!value.equals("string")){
                System.out.println(key + " " + value);
                query.addCriteria(Criteria.where(key).regex(".*" + value + ".*","i"));
            }

        }

        if (getAllProductRequest.getProductId() != 0) {

            query.addCriteria(Criteria.where("productId").is(getAllProductRequest.getProductId()));
        }

        return this.mongoTemplate.find(query, Product.class);
    }

    public Product IsProductAlreadyDeleted(int productId){

       Product product = this.searchProductRepository.findProductByProductId(productId).
               orElseThrow(() -> new BusinessException(ProductFilterBusinessRulesMessages.PRODUCT_NOT_EXISTS));

        if(product.getDeletedDate() != null){
            throw new BusinessException(ProductFilterBusinessRulesMessages.PRODUCT_IS_ALREADY_DELETED);
        }

        return product;
    }
}
