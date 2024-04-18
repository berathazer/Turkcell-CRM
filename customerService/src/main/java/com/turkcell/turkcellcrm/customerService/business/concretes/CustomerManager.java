package com.turkcell.turkcellcrm.customerService.business.concretes;

import com.turkcell.turkcellcrm.customerService.business.abstracts.CustomerService;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.CreateCustomerRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.UpdateCustomerRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.CreatedCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.GetByIdCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.customer.UpdatedCustomerResponse;
import com.turkcell.turkcellcrm.customerService.business.rules.CustomerBusinessRules;
import com.turkcell.turkcellcrm.customerService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.customerService.dataAccess.CustomerRepository;
import com.turkcell.turkcellcrm.customerService.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    private CustomerBusinessRules customerBusinessRules;
    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest createCustomerRequest) {
        this.customerBusinessRules.nationalityNumberCanNotBeDuplicate(createCustomerRequest);

        Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        CreatedCustomerResponse createdCustomerResponse = this.modelMapperService.forResponse().map(this.customerRepository.save(customer), CreatedCustomerResponse.class);
        return createdCustomerResponse;
    }

    @Override
    public List<GetAllCustomerResponse> getAll() {
        List<Customer> customers = this.customerRepository.findAll();
        return  customers.stream().map(customer -> this.modelMapperService.forResponse().
                map(customer, GetAllCustomerResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetByIdCustomerResponse getById(int id) {
        this.customerBusinessRules.isCustomerIdExist(id);
        Optional<Customer> customer =this.customerRepository.findById(id);
        return this.modelMapperService.forResponse().map(customer, GetByIdCustomerResponse.class);
    }

    @Override
    public UpdatedCustomerResponse update(UpdateCustomerRequest updateCustomerRequest) {
        this.customerBusinessRules.nationalityNumberCanNotBeDuplicate(updateCustomerRequest);

        Customer customer = this.modelMapperService.forRequest().map(updateCustomerRequest,Customer.class);
        return this.modelMapperService.forResponse().
                map(this.customerRepository.save(customer), UpdatedCustomerResponse.class);
    }

    @Override
    public void delete(int id) {
        this.customerBusinessRules.isCustomerIdExist(id);
        this.customerRepository.deleteById(id);
    }
}
