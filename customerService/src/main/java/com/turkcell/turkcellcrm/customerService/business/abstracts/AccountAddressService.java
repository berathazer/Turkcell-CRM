package com.turkcell.turkcellcrm.customerService.business.abstracts;

import com.turkcell.turkcellcrm.customerService.entity.Account;

import java.util.Optional;

public interface AccountAddressService {
    void add(Account account);
    void update(Account account);
    void delete(Account account);
}
