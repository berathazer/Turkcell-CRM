package com.turkcell.turkcellcrm.searchService.api.controller;

import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchCustomerService;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchCustomerRepository;
import com.turkcell.turkcellcrm.searchService.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchservice/api/v1/filters/searchcustomer")
@AllArgsConstructor
public class SearchCustomerController {
    private SearchCustomerService searchCustomerService;
    private SearchCustomerRepository searchCustomerRepository;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCustomerResponse> getAll(@ModelAttribute GetAllCustomerRequest getAllCustomerRequest){
        return this.searchCustomerService.getAll(getAllCustomerRequest);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> get(){
        return this.searchCustomerRepository.findAll();
    }
}
