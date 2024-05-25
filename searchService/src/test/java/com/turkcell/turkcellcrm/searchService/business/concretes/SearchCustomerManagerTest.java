package com.turkcell.turkcellcrm.searchService.business.concretes;

import com.turkcell.turkcellcrm.common.events.customer.CustomerCreatedEvent;
import com.turkcell.turkcellcrm.common.events.customer.CustomerUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicFilter;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicQuery;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicSort;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.searchService.business.rules.CustomerFilterBusinessRules;
import com.turkcell.turkcellcrm.searchService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchCustomerRepository;
import com.turkcell.turkcellcrm.searchService.entities.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchCustomerManagerTest {
    @Mock
    CustomerFilterBusinessRules customerFilterBusinessRules;
    @Mock
    ModelMapperService modelMapperService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    SearchCustomerRepository searchCustomerRepository;
    @Mock
    SearchService searchService;
    @InjectMocks
    SearchCustomerManager searchCustomerManager;

    @Test
    void testAdd() {
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerCreatedEvent.setCustomerId(111);
        customerCreatedEvent.setFirstName("John");

        Customer customer = new Customer();
        customer.setCustomerId(111);
        customer.setFirstName("John");

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(customerCreatedEvent,Customer.class)).thenReturn(customer);

        searchCustomerManager.add(customerCreatedEvent);
        verify(modelMapperService.forRequest()).map(customerCreatedEvent,Customer.class);
        verify(searchCustomerRepository).save(customer);
        assertDoesNotThrow(()->searchCustomerManager.add(customerCreatedEvent));

    }

    @Test
    void testGetAll() {
        List<DynamicFilter> filters = Collections.emptyList();
        List<DynamicSort> sorts = Collections.emptyList();
        DynamicQuery dynamicQuery = new DynamicQuery(filters, sorts);

        // Prepare the expected response
        GetAllCustomerResponse response = new GetAllCustomerResponse();
        response.setCustomerId(111);
        response.setFirstName("test");

        // Prepare the mock customer data
        Customer customer = new Customer();
        customer.setCustomerId(111);
        customer.setFirstName("test");

        // Mocking the service and model mapper
        List<Customer> customerList = List.of(customer);
        when(searchService.dynamicSearch(dynamicQuery, Customer.class)).thenReturn(customerList);
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapper.map(customer, GetAllCustomerResponse.class)).thenReturn(response);

        // Execute the method
        List<GetAllCustomerResponse> responses = searchCustomerManager.getAll(dynamicQuery);

        // Verify the interactions
        verify(searchService).dynamicSearch(dynamicQuery, Customer.class);
        verify(modelMapper).map(customer, GetAllCustomerResponse.class);

        // Validate the result
        assertDoesNotThrow(() -> searchCustomerManager.getAll(dynamicQuery));
        assertEquals(1, responses.size());
        assertEquals(response, responses.get(0));

    }

    @Test
    void testUpdate() {
        CustomerUpdatedEvent customerUpdatedEvent = new CustomerUpdatedEvent();
        customerUpdatedEvent.setFirstName("sampleFirstName");
        customerUpdatedEvent.setLastName("sampleLastName");

        Customer customer = new Customer();
        customer.setFirstName("sampleFirstName");
        customer.setLastName("sampleLastName");

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(customerUpdatedEvent,Customer.class)).thenReturn(customer);

        searchCustomerManager.update(customerUpdatedEvent);

        verify(modelMapperService.forRequest()).map(customerUpdatedEvent,Customer.class);
        verify(searchCustomerRepository).save(customer);
        assertDoesNotThrow(()->searchCustomerManager.update(customerUpdatedEvent));
    }

    @Test
    void testDeleteCustomer() {
        int customerId = 111;
        Customer customer = new Customer();
        when(customerFilterBusinessRules.IsCustomerAlreadyDeleted(customerId)).thenReturn(customer);
        searchCustomerManager.deleteCustomer(111);
        verify(customerFilterBusinessRules).IsCustomerAlreadyDeleted(customerId);
        verify(searchCustomerRepository).save(customer);

    }
}