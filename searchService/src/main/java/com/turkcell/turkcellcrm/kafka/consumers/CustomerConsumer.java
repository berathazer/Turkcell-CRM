package com.turkcell.turkcellcrm.kafka.consumers;


import com.turkcell.turkcellcrm.kafka.entities.CreateCustomerEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerConsumer.class);

    @KafkaListener(topics = "customer-created", groupId = "customer_group")
    public void listenCustomerCreated(CreateCustomerEvent createCustomerEvent) {
        // Log the customer creation event
        LOGGER.info("Received customer creation event for ID: {}", createCustomerEvent.getId());

        // Here you can add the logic to handle the customer creation event
        // For example, updating a database, notifying other services, etc.
    }
}
