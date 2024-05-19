package com.turkcell.turkcellcrm.searchService.kafka.consumers;

import com.turkcell.turkcellcrm.common.events.product.ProductCreatedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CatalogConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogConsumer.class);
    private SearchProductService searchProductService;


    @KafkaListener(topics = "catalog-created", groupId = "catalog_group-1")
    public void listenProductCreated(ProductCreatedEvent productCreatedEvent) {

        this.searchProductService.add(productCreatedEvent);
    }
    @KafkaListener(topics = "catalog-updated", groupId = "catalog_group-2")
    public void listenCatalogUpdated(ProductUpdatedEvent catalogUpdatedEvent) {

        this.searchProductService.update(catalogUpdatedEvent);
    }

    @KafkaListener(topics = "catalog-deleted", groupId = "catalog_group-3")
    public void listenCatalogDeleted(ProductUpdatedEvent catalogUpdatedEvent) {

        this.searchProductService.delete(catalogUpdatedEvent.getProductId());

    }
}
