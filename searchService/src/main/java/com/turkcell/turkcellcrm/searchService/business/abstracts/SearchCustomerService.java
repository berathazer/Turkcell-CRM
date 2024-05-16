package com.turkcell.turkcellcrm.searchService.business.abstracts;

import com.turkcell.turkcellcrm.common.events.customer.CustomerCreatedEvent;
import com.turkcell.turkcellcrm.common.events.customer.CustomerUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.searchService.entities.Customer;

import java.util.List;

public interface SearchCustomerService {
    void add(CustomerCreatedEvent customerCreatedEvent);
    List<GetAllCustomerResponse> getAll(GetAllCustomerRequest getAllCustomerRequest);
    void update(CustomerUpdatedEvent customerUpdatedEvent);
    void deleteCustomer(int id);
}
