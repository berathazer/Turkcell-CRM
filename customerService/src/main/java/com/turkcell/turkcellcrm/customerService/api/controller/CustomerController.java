package com.turkcell.turkcellcrm.customerService.api.controller;

import com.turkcell.turkcellcrm.customerService.business.abstracts.CustomerService;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.CreateCustomerRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.UpdateCustomerRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.CreatedCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.GetByIdCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.UpdatedCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerservice/api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCustomerResponse add(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){
        return this.customerService.add(createCustomerRequest);
    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCustomerResponse> getAll(){
        return this.customerService.getAll();
    }

    @GetMapping("/getById/{id}")  //Burayi Request yapabilir miyiz ?
    @ResponseStatus(HttpStatus.OK)
    public GetByIdCustomerResponse getById(@PathVariable int id){
        return this.customerService.getById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCustomerResponse update(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest){
        return this.customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        this.customerService.delete(id);
    }
}
