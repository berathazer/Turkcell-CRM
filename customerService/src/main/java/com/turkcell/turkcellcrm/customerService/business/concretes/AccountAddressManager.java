package com.turkcell.turkcellcrm.customerService.business.concretes;

import com.turkcell.turkcellcrm.customerService.business.abstracts.AccountAddressService;
import com.turkcell.turkcellcrm.customerService.dataAccess.AccountAddressRepository;
import com.turkcell.turkcellcrm.customerService.entity.Account;
import com.turkcell.turkcellcrm.customerService.entity.AccountAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountAddressManager implements AccountAddressService {
    private AccountAddressRepository accountAddressRepository;

    @Override
    public void add(Account account){
        AccountAddress accountAddress  = new AccountAddress();
        accountAddress.setAccount(account);
        accountAddress.setAddress(account.getAccountAddress().getAddress());
        this.accountAddressRepository.save(accountAddress);
    }

    @Override
    public void update(Account account) {
        AccountAddress accountAddress  = new AccountAddress();
        accountAddress.setAccount(account);
        accountAddress.setAddress(account.getAccountAddress().getAddress());
        this.accountAddressRepository.save(accountAddress);
    }

    @Override
    public void delete(Account account) {
        this.accountAddressRepository.deleteById(account.getAccountAddress().getId());
    }

}
