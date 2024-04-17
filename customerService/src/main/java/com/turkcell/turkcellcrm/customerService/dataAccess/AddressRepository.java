package com.turkcell.turkcellcrm.customerService.dataAccess;

import com.turkcell.turkcellcrm.customerService.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
