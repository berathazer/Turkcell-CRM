package com.turkcell.crm.accountService.business.rules;


import com.turkcell.crm.accountService.api.client.CustomerClient;
import com.turkcell.crm.accountService.business.dtos.request.CustomerIdExistRequest;
import com.turkcell.crm.accountService.business.dtos.request.account.CreateAccountRequest;
import com.turkcell.crm.accountService.business.messages.AccountMessages;
import com.turkcell.crm.accountService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.accountService.dataAccess.AccountRepository;
import com.turkcell.crm.accountService.dataAccess.AccountTypeRepository;
import com.turkcell.crm.accountService.entities.Account;
import com.turkcell.crm.accountService.entities.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountBusinessRules {
    private AccountTypeRepository accountTypeRepository;
    private AccountRepository accountRepository;
    private CustomerClient customerClient;

    public Account isAccountExistById(int id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new BusinessException(AccountMessages.ACCOUNT_NOT_FOUND);
        }
        return account.get();
    }

    public void isAccountTypeExist(CreateAccountRequest createAccountRequest){
        Optional<AccountType> accountType = this.accountTypeRepository.findAccountTypeById(createAccountRequest.getAccountTypeId());
        if (accountType.isEmpty()){
            throw new BusinessException(AccountMessages.ACCOUNT_TYPE_NOT_FOUND);
        }
    }



    public Account isAccountAlreadyDeleted(int id){

        Account account = this.isAccountExistById(id);

        if(account.getDeletedDate() != null){
            throw new BusinessException(AccountMessages.ACCOUNT_ALREADY_EXISTS);
        }

        return account;
    }

    public void isCustomerExistById(int id){

        if (!customerClient.getCustomer(id)){
            throw new BusinessException("Boyle bir customer Id yok!");
        }


    }

}
