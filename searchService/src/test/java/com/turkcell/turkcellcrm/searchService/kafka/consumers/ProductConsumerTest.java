package com.turkcell.turkcellcrm.searchService.kafka.consumers;

import com.turkcell.turkcellcrm.common.events.product.ProductCreatedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProductConsumerTest {

    @Mock
    private SearchProductService searchProductService;

    @InjectMocks
    private ProductConsumer productConsumer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listenProductCreated() {
        // Test verilerini ayarlayın
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        // Metodu çağırın
        productConsumer.listenProductCreated(productCreatedEvent);

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchProductService, times(1)).add(productCreatedEvent);
    }

    @Test
    public void listenProductUpdated() {
        // Test verilerini ayarlayın
        ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent();

        // Metodu çağırın
        productConsumer.listenProductUpdated(productUpdatedEvent);

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchProductService, times(1)).update(productUpdatedEvent);
    }

    @Test
    public void listenProductDeleted() {
        // Test verilerini ayarlayın
        ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent();
        productUpdatedEvent.setProductId(1);

        // Metodu çağırın
        productConsumer.listenProductDeleted(productUpdatedEvent);

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchProductService, times(1)).delete(productUpdatedEvent.getProductId());
    }
}