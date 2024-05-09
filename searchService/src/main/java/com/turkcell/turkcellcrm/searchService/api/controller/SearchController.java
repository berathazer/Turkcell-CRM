package com.turkcell.turkcellcrm.searchService.api.controller;

import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchRepository;
import com.turkcell.turkcellcrm.searchService.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/searchservice/api/v1/filter")
@AllArgsConstructor
public class SearchController {
    private SearchService searchService;
    private SearchRepository searchRepository;
    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCustomerResponse> getAll(@ModelAttribute GetAllCustomerRequest getAllCustomerRequest){
        return this.searchService.getAll(getAllCustomerRequest);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String get(){
        Optional<Customer> customer = searchRepository.findById("663a656dae0148586dc525f5");
        return "663a656dae0148586dc525f5";
    }
}
