package com.turkcell.turkcellcrm.searchService.dataAccess;

import com.turkcell.turkcellcrm.searchService.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SearchRepository extends MongoRepository<Customer,String> {
    Optional<Customer> findByCustomerId(String customerId);
    void deleteByCustomerId(int customerId);
}
