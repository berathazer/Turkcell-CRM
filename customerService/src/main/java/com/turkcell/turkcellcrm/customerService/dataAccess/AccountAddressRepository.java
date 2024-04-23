package com.turkcell.turkcellcrm.customerService.dataAccess;

import com.turkcell.turkcellcrm.customerService.entity.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAddressRepository extends JpaRepository<AccountAddress,Integer> {
}
