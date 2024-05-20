package com.turkcell.turkcellcrm.searchService.api.controller;

import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchCatalogService;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchProductService;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCatalogRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllProductRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCatalogResponse;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchservice/api/v1/filters/searchcatalog")
@AllArgsConstructor
public class SearchCatalogController {

    private SearchCatalogService searchCatalogService;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCatalogResponse> getAll(){
        return this.searchCatalogService.getAllCatalog();
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductResponse> getAll(@ModelAttribute GetAllCatalogRequest getAllCatalogRequest){
        return this.searchCatalogService.getFilteredCatalog(getAllCatalogRequest);
    }
}
