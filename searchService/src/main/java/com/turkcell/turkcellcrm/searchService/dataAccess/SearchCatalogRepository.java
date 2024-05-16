package com.turkcell.turkcellcrm.searchService.dataAccess;

import com.turkcell.turkcellcrm.searchService.entities.Catalog;
import com.turkcell.turkcellcrm.searchService.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SearchCatalogRepository extends MongoRepository<Catalog, String> {
    Optional<Catalog> findCatalogByCatalogId(int catalogId);
}
