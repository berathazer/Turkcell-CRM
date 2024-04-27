package com.turkcell.turkcellcrm.customerService.kafka.producers;


import com.turkcell.turkcellcrm.customerService.kafka.entities.CreateCustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomerProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProducer.class);
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public CustomerProducer(KafkaTemplate<String,Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    // customer oluştuğunda POST
    public void sendMessage(CreateCustomerEvent createCustomerEvent){
        LOGGER.info(String.format("Customer added =>%s",createCustomerEvent.toString()));
        String TOPIC = "customer-topic"; // Kafka topic name

        kafkaTemplate.send(TOPIC, createCustomerEvent);
        System.out.println("Published message: " + createCustomerEvent.getEmail());

       // Message<CreateCustomerEvent> message = MessageBuilder.withPayload(createCustomerEvent)
         //       .setHeader(KafkaHeaders.TOPIC,"customer-created")
          //      .build();

       // kafkaTemplate.send(message);
    }

    // TODO: delete ve update içinde event oluştur.
}