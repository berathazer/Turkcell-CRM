package com.turkcell.turkcellcrm.searchService.kafka.consumers;

import com.turkcell.turkcellcrm.common.events.customer.CustomerCreatedEvent;
import com.turkcell.turkcellcrm.common.events.customer.CustomerDeletedEvent;
import com.turkcell.turkcellcrm.common.events.customer.CustomerUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchCustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class CustomerConsumerTest {

    @Mock
    private SearchCustomerService searchCustomerService;

    @InjectMocks
    private CustomerConsumer customerConsumer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listenCustomerCreated() {
        // Test verilerini ayarlayın
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();

        // Metodu çağırın
        customerConsumer.listenCustomerCreated(customerCreatedEvent);

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchCustomerService, times(1)).add(customerCreatedEvent);
    }

    @Test
    public void listenCustomerUpdated() {
        // Test verilerini ayarlayın
        CustomerUpdatedEvent customerUpdatedEvent = new CustomerUpdatedEvent();

        // Metodu çağırın
        customerConsumer.listenCustomerUpdated(customerUpdatedEvent);

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchCustomerService, times(1)).update(customerUpdatedEvent);
    }

    @Test
    public void listenCustomerDeleted() {
        // Test verilerini ayarlayın
        CustomerDeletedEvent customerDeletedEvent = new CustomerDeletedEvent();
        customerDeletedEvent.setCustomerId(1);

        // Metodu çağırın
        customerConsumer.listenCustomerDeleted(customerDeletedEvent);

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchCustomerService, times(1)).deleteCustomer(customerDeletedEvent.getCustomerId());
    }
}