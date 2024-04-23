package com.turkcell.turkcellcrm.customerService.business.rules;

import com.turkcell.turkcellcrm.customerService.business.dtos.request.account.CreateAccountRequest;
import com.turkcell.turkcellcrm.customerService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.customerService.dataAccess.*;
import com.turkcell.turkcellcrm.customerService.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountBusinessRules {
    private IndividualCustomerRepository individualCustomerRepository;
    private AccountTypeRepository accountTypeRepository;
    private AddressRepository addressRepository;
    private AccountRepository accountRepository;

    public void isAccountExistById(int id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new BusinessException("Account not found");
        }
    }
    public void isCustomerExist(CreateAccountRequest createAccountRequest){
        Optional<IndividualCustomer> individualCustomer = this.individualCustomerRepository.findIndividualCustomerById(createAccountRequest.getCustomerId());
        if (individualCustomer.isEmpty()){
            throw new BusinessException("Customer not found");
        }
    }
    public void isAccountTypeExist(CreateAccountRequest createAccountRequest){
        Optional<AccountType> accountType = this.accountTypeRepository.findAccountTypeById(createAccountRequest.getCustomerId());
        if (accountType.isEmpty()){
            throw new BusinessException("Account type not found");
        }
    }
    public void isAddressExist(CreateAccountRequest createAccountRequest){
        Optional<Address> address = this.addressRepository.findAddressById(createAccountRequest.getCustomerId());
        if (address.isEmpty()){
            throw new BusinessException("Address not found");
        }
    }
}
