package com.turkcell.turkcellcrm.searchService.business.concretes;

import com.turkcell.turkcellcrm.common.events.CustomerCreatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.searchService.business.rules.CustomerFilterBusinessRules;
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
    private CustomerFilterBusinessRules customerFilterBusinessRules;
    @Override
    public void add(CustomerCreatedEvent customerCreatedEvent) {
        this.customerFilterBusinessRules.IsCustomerIdExistById(customerCreatedEvent);

        Customer customer = this.modelMapperService.forRequest().map(customerCreatedEvent, Customer.class);
        customer.setId(null); // idyi null yapıyoruz mongodb otomatik objectid olarak ekleyecek
        this.searchRepository.save(customer);
    }
    @Override
    public List<GetAllCustomerResponse> getAll(GetAllCustomerRequest getAllCustomerRequest) {
        List<Customer> customerList = this.customerFilterBusinessRules.filterCustomer(getAllCustomerRequest);

        return customerList.stream().
                map(customer -> this.modelMapperService.forResponse().
                map(customer, GetAllCustomerResponse.class)).toList();
    }
    @Override
    public void update(Customer customer) {
        this.searchRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        this.searchRepository.deleteCustomersByCustomerId(id);
    }
}
