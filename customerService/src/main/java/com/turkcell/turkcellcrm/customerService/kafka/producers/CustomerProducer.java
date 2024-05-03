package com.turkcell.turkcellcrm.customerService.kafka.producers;


import com.turkcell.turkcellcrm.customerService.kafka.entities.CreateCustomerEvent;
import com.turkcell.turkcellcrm.customerService.kafka.entities.DeleteCustomerEvent;
import com.turkcell.turkcellcrm.customerService.kafka.entities.UpdateCustomerEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProducer.class);
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void sendCreatedMessage(CreateCustomerEvent createCustomerEvent){
        LOGGER.info(String.format("Customer added =>%s",createCustomerEvent.toString()));

        Message<CreateCustomerEvent> message = MessageBuilder.withPayload(createCustomerEvent)
               .setHeader(KafkaHeaders.TOPIC,"customer-created")
                .build();

        kafkaTemplate.send(message);
    }
    public void sendUpdatedMessage(UpdateCustomerEvent updateCustomerEvent){
        LOGGER.info(String.format("Customer updated =>%s",updateCustomerEvent.toString()));

        Message<UpdateCustomerEvent> message = MessageBuilder.withPayload(updateCustomerEvent)
                .setHeader(KafkaHeaders.TOPIC,"customer-updated")
                .build();

        kafkaTemplate.send(message);
    }
    public void sendDeletedMessage(int id){
        DeleteCustomerEvent deleteCustomerEvent = new DeleteCustomerEvent(id);
        LOGGER.info(String.format("Customer deleted =>%s",deleteCustomerEvent.toString()));

        Message<DeleteCustomerEvent> message = MessageBuilder.withPayload(deleteCustomerEvent)
                .setHeader(KafkaHeaders.TOPIC,"customer-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}