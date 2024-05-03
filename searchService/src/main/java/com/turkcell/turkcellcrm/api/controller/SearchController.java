package com.turkcell.turkcellcrm.api.controller;

import com.turkcell.turkcellcrm.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.business.dto.response.GetAllCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchservice/api/v1/filter")
@AllArgsConstructor
public class SearchController {
    private SearchService searchService;

    @PostMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCustomerResponse> getAll(@Valid @RequestBody GetAllCustomerRequest getAllCustomerRequest){
        return this.searchService.getAll(getAllCustomerRequest);
    }
}
