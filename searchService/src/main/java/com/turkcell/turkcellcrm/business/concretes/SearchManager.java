package com.turkcell.turkcellcrm.business.concretes;

import com.turkcell.turkcellcrm.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.business.dto.response.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.entities.Customer;
import com.turkcell.turkcellcrm.dataAccess.SearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchManager implements SearchService {
    private SearchRepository searchRepository;
    private ModelMapperService modelMapperService;
    @Override
    public void add(Customer customer) {
        this.searchRepository.save(customer);
    }
    @Override
    public List<GetAllCustomerResponse> getAll(GetAllCustomerRequest getAllCustomerRequest) {
        List<Customer> customers = this.searchRepository.findAll();
        return customers.stream().map(customer -> this.modelMapperService.forResponse().
                map(customer, GetAllCustomerResponse.class)).toList();
    }
    @Override
    public void update(Customer customer) {
        this.searchRepository.save(customer);
    }

    @Override
    public void delete(int id) {
        this.searchRepository.deleteById(id);
    }
}
