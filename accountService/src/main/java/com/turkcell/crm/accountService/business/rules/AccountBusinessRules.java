package com.turkcell.crm.accountService.business.rules;


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
    private RestTemplate restTemplate;

    public void isAccountExistById(int id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new BusinessException(AccountMessages.ACCOUNT_NOT_FOUND);
        }
    }

    public void isAccountTypeExist(CreateAccountRequest createAccountRequest){
        Optional<AccountType> accountType = this.accountTypeRepository.findAccountTypeById(createAccountRequest.getAccountTypeId());
        if (accountType.isEmpty()){
            throw new BusinessException(AccountMessages.ACCOUNT_TYPE_NOT_FOUND);
        }
    }

    public void isCustomerIdExist(CreateAccountRequest createAccountRequest) {
        String url = "http://localhost:9002/customerservice/api/v1/customers/account/get/"
                + createAccountRequest.getCustomerId();
        try {
            ResponseEntity<Boolean> response = this.restTemplate.getForEntity(url, Boolean.class);
            if ( ! (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody())) {
                throw new BusinessException(AccountMessages.CUSTOMER_ID_NOT_FOUND);
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new BusinessException(AccountMessages.ACCOUNT_TYPE_NOT_FOUND);
        } catch (RestClientException e) {
            throw new BusinessException(AccountMessages.ERROR_CHECKING_CUSTOMER_ID_EXISTENCE);
        }
    }
}
