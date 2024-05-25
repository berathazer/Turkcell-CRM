package com.turkcell.crm.catalogService.kafka.producers;

import com.turkcell.turkcellcrm.common.events.product.ProductCreatedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductDeletedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductUpdatedEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductProducerTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private Logger logger;

    @InjectMocks
    private ProductProducer productProducer;

    @Captor
    private ArgumentCaptor<Message<Object>> messageCaptor;

    @BeforeEach
    void setUp() {
        productProducer = new ProductProducer(kafkaTemplate);
    }

    @Test
    void testSendCreatedMessage() {
        // Given
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(1, 1, "Product");

        // When
        productProducer.sendCreatedMessage(productCreatedEvent);

        // Then
        verify(logger, times(1)).info(String.format("Product added =>%s", productCreatedEvent.toString()));
        verify(kafkaTemplate, times(1)).send(messageCaptor.capture());

        Message<Object> capturedMessage = messageCaptor.getValue();
        assertEquals(productCreatedEvent, capturedMessage.getPayload());
        assertEquals("product-created", capturedMessage.getHeaders().get(KafkaHeaders.TOPIC));
    }

    @Test
    void testSendUpdatedMessage() {
        ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent(1,"Update Product",1);

        productProducer.sendUpdatedMessage(productUpdatedEvent);

        verify(logger, times(1)).info(String.format("Product updated =>%s", productUpdatedEvent.toString()));
        verify(kafkaTemplate, times(1)).send(messageCaptor.capture());

        Message<Object> capturedMessage = messageCaptor.getValue();
        assertEquals(productUpdatedEvent, capturedMessage.getPayload());
        assertEquals("product-updated", capturedMessage.getHeaders().get(KafkaHeaders.TOPIC));
    }

    @Test
    void testSendDeletedMessage() {
        ProductDeletedEvent productDeletedEvent = new ProductDeletedEvent(1);

        productProducer.sendDeletedMessage(productDeletedEvent);

        verify(logger, times(1)).info(String.format("Product deleted =>%s", productDeletedEvent.toString()));
        verify(kafkaTemplate, times(1)).send(messageCaptor.capture());

        Message<Object> capturedMessage = messageCaptor.getValue();
        assertEquals(productDeletedEvent, capturedMessage.getPayload());
        assertEquals("product-deleted", capturedMessage.getHeaders().get(KafkaHeaders.TOPIC));
    }
}