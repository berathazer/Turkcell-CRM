package com.turkcell.turkcellcrm.customerService.business.rules;

import com.turkcell.turkcellcrm.customerService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.customerService.dataAccess.AccountTypeRepository;
import com.turkcell.turkcellcrm.customerService.entity.AccountType;
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
            throw new BusinessException("Account Type not found");
        }
    }

}
