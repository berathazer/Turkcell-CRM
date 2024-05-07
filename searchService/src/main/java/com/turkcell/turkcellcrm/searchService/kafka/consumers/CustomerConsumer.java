package com.turkcell.turkcellcrm.searchService.kafka.consumers;


import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.common.events.CustomerCreatedEvent;
import com.turkcell.turkcellcrm.common.events.CustomerDeletedEvent;
import com.turkcell.turkcellcrm.common.events.CustomerUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.searchService.entities.Customer;
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
    private ModelMapperService modelMapperService;

    @KafkaListener(topics = "customer-created-test", groupId = "customer_group-1")
    public void listenCustomerCreated(CustomerCreatedEvent customerCreatedEvent) {
        //LOGGER.info("KAFKADAN MESAJ GELDIIIII : {}",customerCreatedEvent);
        Customer customer = this.modelMapperService.forRequest().map(customerCreatedEvent, Customer.class);
        customer.setId(null); // idyi null yapıyoruz mongodb otomatik objectid olarak ekleyecek
        this.searchService.add(customer);
    }
    //    @KafkaListener(topics = "customer-updated", groupId = "customer_group-2")
    //    public void listenCustomerUpdated(CustomerUpdatedEvent customerUpdatedEvent) {
    //        Customer customer = this.modelMapperService.forRequest().map(customerUpdatedEvent, Customer.class);
    //        this.searchService.update(customer);
    //    }
    //    @KafkaListener(topics = "customer-deleted", groupId = "customer_group-3")
    //    public void listenCustomerDeleted(CustomerDeletedEvent customerDeletedEvent) {
    //        this.searchService.deleteCustomer(customerDeletedEvent.getCustomerId());
    //    }
}
