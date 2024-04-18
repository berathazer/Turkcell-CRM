package com.turkcell.turkcellcrm.customerService.dataAccess;

import com.turkcell.turkcellcrm.customerService.entity.Address;
import com.turkcell.turkcellcrm.customerService.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    Optional<Contact> findByCustomerId(int id);
}
