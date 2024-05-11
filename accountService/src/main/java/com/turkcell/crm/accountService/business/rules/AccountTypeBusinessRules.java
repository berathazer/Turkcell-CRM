package com.turkcell.crm.accountService.business.rules;


import com.turkcell.crm.accountService.business.messages.AccountMessages;
import com.turkcell.crm.accountService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.crm.accountService.dataAccess.AccountTypeRepository;
import com.turkcell.crm.accountService.entities.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountTypeBusinessRules {
    private AccountTypeRepository accountTypeRepository;

    public void isAccountTypeExistById(int id) {
        Optional<AccountType> accountType = this.accountTypeRepository.findById(id);
        if (accountType.isEmpty()) {
            throw new BusinessException(AccountMessages.ACCOUNT_TYPE_NOT_FOUND);
        }
    }

}
