package com.turkcell.turkcellcrm.customerService.business.abstracts;

import com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.CreateCustomerRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.UpdateCustomerRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.CreatedCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.GetByIdCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.UpdatedCustomerResponse;

import java.util.List;

public interface CustomerService {
    CreatedCustomerResponse add(CreateCustomerRequest createCustomerRequest);
    List<GetAllCustomerResponse> getAll();
    GetByIdCustomerResponse getById(int id);
    UpdatedCustomerResponse update(UpdateCustomerRequest updateCustomerRequest);
    void delete(int id);
}
