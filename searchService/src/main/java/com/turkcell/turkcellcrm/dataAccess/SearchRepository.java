package com.turkcell.turkcellcrm.dataAccess;

import com.turkcell.turkcellcrm.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SearchRepository extends MongoRepository<Customer,Integer> {

}
