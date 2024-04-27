package com.turkcell.turkcellcrm.repositories;

import com.turkcell.turkcellcrm.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SearchRepository extends MongoRepository<Customer,Integer> {

}
