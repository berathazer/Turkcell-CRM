package com.turkcell.turkcellcrm.searchService.kafka.consumers;

import com.turkcell.turkcellcrm.common.events.catalog.CatalogCreatedEvent;
import com.turkcell.turkcellcrm.common.events.catalog.CatalogDeletedEvent;
import com.turkcell.turkcellcrm.common.events.catalog.CatalogUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchCatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CatalogConsumerTest {

    @Mock
    private SearchCatalogService searchCatalogService;

    @InjectMocks
    private CatalogConsumer catalogConsumer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListenCatalogCreated() {
        // Test verilerini ayarlayın
        CatalogCreatedEvent catalogCreatedEvent = new CatalogCreatedEvent();

        // Metodu çağırın
        catalogConsumer.listenCatalogCreated(catalogCreatedEvent);

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchCatalogService, times(1)).add(catalogCreatedEvent);
    }

    @Test
    public void testListenCatalogUpdated() {
        // Test verilerini ayarlayın
        CatalogUpdatedEvent catalogUpdatedEvent = new CatalogUpdatedEvent();

        // Metodu çağırın
        catalogConsumer.listenCatalogUpdated(catalogUpdatedEvent);

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchCatalogService, times(1)).update(catalogUpdatedEvent);
    }

    @Test
    public void testListenCatalogDeleted() {
        // Test verilerini ayarlayın
        CatalogDeletedEvent catalogDeletedEvent = new CatalogDeletedEvent();
        catalogDeletedEvent.setId(1);

        // Metodu çağırın
        catalogConsumer.listenCatalogDeleted(catalogDeletedEvent);

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchCatalogService, times(1)).delete(catalogDeletedEvent.getId());
    }
}