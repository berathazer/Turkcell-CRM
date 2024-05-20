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

    public Product IsProductAlreadyDeleted(int productId){

       Product product = this.searchProductRepository.findProductByProductId(productId).
               orElseThrow(() -> new BusinessException(ProductFilterBusinessRulesMessages.PRODUCT_NOT_EXISTS));

        if(product.getDeletedDate() != null){
            throw new BusinessException(ProductFilterBusinessRulesMessages.PRODUCT_IS_ALREADY_DELETED);
        }

        return product;
    }
}
