package com.turkcell.turkcellcrm.kafka.consumers;


import com.turkcell.turkcellcrm.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.entities.Customer;
import com.turkcell.turkcellcrm.kafka.entities.CreateCustomerEvent;
import com.turkcell.turkcellcrm.kafka.entities.DeleteCustomerEvent;
import com.turkcell.turkcellcrm.kafka.entities.UpdateCustomerEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerConsumer.class);
    private SearchService searchService;
    private ModelMapperService modelMapperService;

    @KafkaListener(topics = "customer-created", groupId = "customer_group")
    public void listenCustomerCreated(CreateCustomerEvent createCustomerEvent) {
        Customer customer = this.modelMapperService.forRequest().map(createCustomerEvent, Customer.class);
        this.searchService.add(customer);
    }
    @KafkaListener(topics = "customer-updated", groupId = "customer_group")
    public void listenCustomerUpdated(UpdateCustomerEvent updateCustomerEvent) {
        Customer customer = this.modelMapperService.forRequest().map(updateCustomerEvent, Customer.class);
        this.searchService.update(customer);
    }
    @KafkaListener(topics = "customer-deleted", groupId = "customer_group")
    public void listenCustomerDeleted(DeleteCustomerEvent deleteCustomerEvent) {
        this.searchService.delete(deleteCustomerEvent.getId());
    }
}
