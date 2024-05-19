package com.turkcell.turkcellcrm.searchService.business.concretes;

import com.turkcell.turkcellcrm.common.events.product.ProductCreatedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchProductService;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllProductRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllProductResponse;
import com.turkcell.turkcellcrm.searchService.business.rules.ProductFilterBusinessRules;
import com.turkcell.turkcellcrm.searchService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchProductRepository;
import com.turkcell.turkcellcrm.searchService.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchProductManager implements SearchProductService {
    private SearchProductRepository searchProductRepository;
    private ModelMapperService modelMapperService;
    private ProductFilterBusinessRules productFilterBusinessRules;

    @Override
    public void add(ProductCreatedEvent productCreatedEvent) {

        this.productFilterBusinessRules.IsProductIdExistById(productCreatedEvent);

        Product product = this.modelMapperService.forRequest().map(productCreatedEvent, Product.class);
        product.setId(null);
        this.searchProductRepository.save(product);
    }

    @Override
    public List<GetAllProductResponse> getAll(GetAllProductRequest getAllProductRequest) {

        List<Product> productList = this.productFilterBusinessRules.filterProduct(getAllProductRequest);

        return productList.stream().
                map(catalog -> this.modelMapperService.forResponse().
                        map(productList, GetAllProductResponse.class)).toList();
    }

    @Override
    public void update(ProductUpdatedEvent productUpdatedEvent) {

        this.productFilterBusinessRules.IsProductIdExistById(productUpdatedEvent);

        Product product = this.modelMapperService.forRequest().map(productUpdatedEvent, Product.class);
        this.searchProductRepository.save(product);
    }

    @Override
    public void delete(int productId) {

        Product product = this.productFilterBusinessRules.IsProductAlreadyDeleted(productId);
        product.setDeletedDate(LocalDateTime.now());

        this.searchProductRepository.save(product);

    }


}
