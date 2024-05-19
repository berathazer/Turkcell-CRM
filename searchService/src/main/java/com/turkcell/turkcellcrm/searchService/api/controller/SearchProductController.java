package com.turkcell.turkcellcrm.searchService.api.controller;

import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchProductService;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllProductRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllProductResponse;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchProductRepository;
import com.turkcell.turkcellcrm.searchService.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchservice/api/v1/filters/searchcatalog")
@AllArgsConstructor
public class SearchProductController {
    private SearchProductService searchProductService;
    private SearchProductRepository searchProductRepository;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductResponse> getAll(@ModelAttribute GetAllProductRequest getAllProductRequest){
        return this.searchProductService.getAll(getAllProductRequest);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> get(){
        return this.searchProductRepository.findAll();
    }
}
