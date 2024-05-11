package com.turkcell.crm.accountService.business.rules;


import com.turkcell.crm.accountService.business.dtos.request.account.CreateAccountRequest;
import com.turkcell.crm.accountService.business.messages.AccountMessages;
import com.turkcell.crm.accountService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.accountService.dataAccess.AccountRepository;
import com.turkcell.crm.accountService.dataAccess.AccountTypeRepository;
import com.turkcell.crm.accountService.entities.Account;
import com.turkcell.crm.accountService.entities.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountBusinessRules {
    private AccountTypeRepository accountTypeRepository;
    private AccountRepository accountRepository;

    public void isAccountExistById(int id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new BusinessException(AccountMessages.ACCOUNT_NOT_FOUND);
        }
    }

    public void isAccountTypeExist(CreateAccountRequest createAccountRequest){
        Optional<AccountType> accountType = this.accountTypeRepository.findAccountTypeById(createAccountRequest.getAccountTypeId());
        if (accountType.isEmpty()){
            throw new BusinessException(AccountMessages.ACCOUNTTYPE_NOT_FOUND);
        }
    }

}
