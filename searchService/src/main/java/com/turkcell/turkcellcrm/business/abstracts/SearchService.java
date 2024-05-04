package com.turkcell.turkcellcrm.business.abstracts;

import com.turkcell.turkcellcrm.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.business.dto.response.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.entities.Customer;

import java.util.List;

public interface SearchService {
    void add(Customer customer);
    List<GetAllCustomerResponse> getAll(GetAllCustomerRequest getAllCustomerRequest);
    void update(Customer customer);
    void delete(int id);

}
