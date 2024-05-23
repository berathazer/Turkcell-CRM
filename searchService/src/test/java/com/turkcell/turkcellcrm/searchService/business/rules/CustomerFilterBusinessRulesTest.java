package com.turkcell.turkcellcrm.searchService.business.rules;

import com.turkcell.turkcellcrm.searchService.business.messages.CustomerFilterBusinessRulesMessages;
import com.turkcell.turkcellcrm.searchService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchCustomerRepository;
import com.turkcell.turkcellcrm.searchService.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerFilterBusinessRulesTest {

    @Mock
    private SearchCustomerRepository searchCustomerRepository;

    @InjectMocks
    private CustomerFilterBusinessRules customerFilterBusinessRules;

    @BeforeEach
    void setUp() {
        customerFilterBusinessRules = new CustomerFilterBusinessRules(searchCustomerRepository);
    }

    @Test
    void testIsCustomerAlreadyDeleted_CustomerExistsAndNotDeleted() {
        int customerId = 100;

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setFirstName("Deniz");
        customer.setLastName("Parlar");
        customer.setAccountAccountNumber("123456");
        customer.setMiddleName("-");
        customer.setMobilePhoneNumber("45784121");

        // Mock the repository to return the customer
        when(searchCustomerRepository.findCustomersByCustomerId(customerId)).thenReturn(Optional.of(customer));

        // Perform the business rule check
        Customer result = customerFilterBusinessRules.IsCustomerAlreadyDeleted(customerId);

        // Verify the result
        assertEquals(customer, result);
        verify(searchCustomerRepository).findCustomersByCustomerId(customerId);
    }

    @Test
    void testIsCustomerAlreadyDeleted_CustomerDoesNotExist() {
        int customerId = 200;

        // Mock the repository to return an empty Optional
        when(searchCustomerRepository.findCustomersByCustomerId(customerId)).thenReturn(Optional.empty());

        // Expect an exception
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            customerFilterBusinessRules.IsCustomerAlreadyDeleted(customerId);
        });

        // Verify the exception message
        assertEquals(CustomerFilterBusinessRulesMessages.CUSTOMER_NOT_EXISTS, exception.getMessage());
        verify(searchCustomerRepository).findCustomersByCustomerId(customerId);
    }

    @Test
    void testIsCustomerAlreadyDeleted_CustomerAlreadyDeleted() {
        int customerId = 300;

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setFirstName("Deniz");
        customer.setLastName("Parlar");
        customer.setAccountAccountNumber("123456");
        customer.setMiddleName("-");
        customer.setMobilePhoneNumber("45784121");
        customer.setDeletedDate(LocalDateTime.now());

        // Mock the repository to return the customer
        when(searchCustomerRepository.findCustomersByCustomerId(customerId)).thenReturn(Optional.of(customer));

        // Expect an exception
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            customerFilterBusinessRules.IsCustomerAlreadyDeleted(customerId);
        });

        // Verify the exception message
        assertEquals(CustomerFilterBusinessRulesMessages.CUSTOMER_IS_ALREADY_DELETED, exception.getMessage());
        verify(searchCustomerRepository).findCustomersByCustomerId(customerId);
    }
}