package com.turkcell.turkcellcrm.searchService.api.controller;

import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchProductService;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllProductRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchservice/api/v1/filters/searchproduct")
@AllArgsConstructor
public class SearchProductController {

    private SearchProductService searchProductService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductResponse> getAll(@ModelAttribute GetAllProductRequest getAllProductRequest){
        return this.searchProductService.getAll(getAllProductRequest);
    }
}
