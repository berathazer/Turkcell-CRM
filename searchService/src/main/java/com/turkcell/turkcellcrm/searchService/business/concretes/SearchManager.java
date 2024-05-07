package com.turkcell.turkcellcrm.searchService.business.concretes;

import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.searchService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.searchService.entities.Customer;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void deleteCustomer(int id) {
        this.searchRepository.deleteByCustomerId(id);
    }
}
