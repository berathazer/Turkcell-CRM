package com.turkcell.turkcellcrm.customerService.api.controller;

import com.turkcell.turkcellcrm.customerService.business.abstracts.AddressService;
import com.turkcell.turkcellcrm.customerService.business.abstracts.ContactService;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.address.CreateAddressRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.address.UpdateAddressRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.contact.CreateContactRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.contact.UpdateContactRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.address.CreatedAddressResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.address.GetAllAddressResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.address.GetByIdAddressResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.address.UpdatedAddressResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.CreatedContactResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.GetAllContactResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.GetByIdContactResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.UpdatedContactResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
@AllArgsConstructor
public class ContactController {
    private ContactService contactService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedContactResponse add(@Valid @RequestBody CreateContactRequest createContactRequest){
        return this.contactService.add(createContactRequest);
    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllContactResponse> getAll(){
        return this.contactService.getAll();
    }

    @GetMapping("/getById/{id}")  //Burayi Request yapabilir miyiz ?
    @ResponseStatus(HttpStatus.OK)
    public GetByIdContactResponse getById(@PathVariable int id){
        return this.contactService.getById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedContactResponse update(@Valid @RequestBody UpdateContactRequest updateContactRequest){
        return this.contactService.update(updateContactRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        this.contactService.delete(id);
    }
}
