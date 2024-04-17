package com.turkcell.turkcellcrm.customerService.dataAccess;

import com.turkcell.turkcellcrm.customerService.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
