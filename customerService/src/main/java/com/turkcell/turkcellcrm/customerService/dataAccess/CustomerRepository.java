package com.turkcell.turkcellcrm.customerService.dataAccess;

import com.turkcell.turkcellcrm.customerService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
