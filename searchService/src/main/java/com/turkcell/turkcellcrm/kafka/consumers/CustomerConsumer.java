package com.turkcell.turkcellcrm.kafka.consumers;


import com.turkcell.turkcellcrm.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.entities.Customer;
import com.turkcell.turkcellcrm.kafka.entities.CreateCustomerEvent;
import com.turkcell.turkcellcrm.repositories.SearchRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerConsumer.class);
    private SearchService searchService;


    @KafkaListener(topics = "customer-created",groupId = "customer_group")
    public void listenCustomerCreated(CreateCustomerEvent createCustomerEvent) {
        Customer customer = new Customer();
        customer.setId(createCustomerEvent.getId());
        customer.setEmail(createCustomerEvent.getEmail());
        customer.setMobilePhoneNumber(createCustomerEvent.getPhoneNumber());
        this.searchService.add(customer);
    }
}
