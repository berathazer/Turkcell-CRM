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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchCustomerManagerTest {

    @Mock
    private SearchCustomerRepository searchCustomerRepository;

    @Mock
    private CustomerFilterBusinessRules customerFilterBusinessRules;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private SearchService searchService;

    @InjectMocks
    private SearchCustomerManager searchCustomerManager;


    @Test
    void testAdd() {
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        Customer customer = new Customer();


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

        Customer customer = new Customer();
        List<Customer> customerList = Arrays.asList(customer);
        GetAllCustomerResponse response = new GetAllCustomerResponse();

        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(searchService.dynamicSearch(dynamicQuery, Customer.class)).thenReturn(customerList);
        when(modelMapperService.forResponse().map(customer, GetAllCustomerResponse.class)).thenReturn(response);

        List<GetAllCustomerResponse> responses = searchCustomerManager.getAll(dynamicQuery);

        verify(searchService).dynamicSearch(dynamicQuery, Customer.class);
        verify(modelMapperService.forResponse()).map(customer, GetAllCustomerResponse.class);
    }

    @Test
    void testUpdate() {

        CustomerUpdatedEvent customerUpdatedEvent = new CustomerUpdatedEvent();
        Customer customer = new Customer();

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapperService.forRequest().map(customerUpdatedEvent, Customer.class)).thenReturn(customer);

        searchCustomerManager.update(customerUpdatedEvent);

        verify(modelMapperService.forRequest()).map(customerUpdatedEvent, Customer.class);
        verify(searchCustomerRepository).save(customer);
        assertDoesNotThrow(()->searchCustomerManager.update(customerUpdatedEvent));
    }

    @Test
    void testDelete_Successful() {
        int customerId = 150;

        Customer customer = new Customer();

        // Mock the business rule to return the product
        when(customerFilterBusinessRules.IsCustomerAlreadyDeleted(customerId)).thenReturn(customer);

        // Perform the delete operation
        searchCustomerManager.deleteCustomer(customerId);

        // Verify that the methods were called
        verify(customerFilterBusinessRules).IsCustomerAlreadyDeleted(customerId);
        verify(searchCustomerRepository).save(customer);

        // Check if the deleted date is set
        assertNotNull(customer.getDeletedDate());
    }
}