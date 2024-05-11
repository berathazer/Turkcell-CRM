package com.turkcell.crm.accountService.business.concretes;


import com.turkcell.crm.accountService.business.abstracts.AccountService;
import com.turkcell.crm.accountService.business.dtos.request.account.CreateAccountRequest;
import com.turkcell.crm.accountService.business.dtos.request.account.UpdateAccountRequest;
import com.turkcell.crm.accountService.business.dtos.response.account.CreatedAccountResponse;
import com.turkcell.crm.accountService.business.dtos.response.account.GetAllAccountResponse;
import com.turkcell.crm.accountService.business.dtos.response.account.GetByIdAccountResponse;
import com.turkcell.crm.accountService.business.dtos.response.account.UpdatedAccountResponse;
import com.turkcell.crm.accountService.business.rules.AccountBusinessRules;
import com.turkcell.crm.accountService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.accountService.dataAccess.AccountRepository;
import com.turkcell.crm.accountService.entities.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountManager implements AccountService {
    private AccountRepository accountRepository;
    private ModelMapperService modelMapperService;
    private AccountBusinessRules accountBusinessRules;


    @Override
    public CreatedAccountResponse add(CreateAccountRequest createAccountRequest) {
        this.accountBusinessRules.isAccountTypeExist(createAccountRequest);
        this.accountBusinessRules.isCustomerIdExist(createAccountRequest);

        Account account =this.modelMapperService.forRequest().map(createAccountRequest, Account.class);
        this.accountRepository.save(account);

        return this.modelMapperService.forResponse().map(account, CreatedAccountResponse.class);
    }

    @Override
    public GetByIdAccountResponse getById(int id) {
        this.accountBusinessRules.isAccountExistById(id);
        return this.modelMapperService.forResponse()
                .map(this.accountRepository.findById(id), GetByIdAccountResponse.class);
    }

    @Override
    public List<GetAllAccountResponse> getAll() {
        List<Account> accounts = this.accountRepository.findAll();
        return accounts.stream().map(account -> this.modelMapperService.forResponse().
                map(account, GetAllAccountResponse.class)).toList();
    }

    @Override
    public UpdatedAccountResponse update(UpdateAccountRequest updateAccountRequest) {
        this.accountBusinessRules.isAccountExistById(updateAccountRequest.getId());
        Account account = this.modelMapperService.forRequest().
                map(updateAccountRequest, Account.class);

        return this.modelMapperService.forResponse().
                map(this.accountRepository.save(account), UpdatedAccountResponse.class);
    }

    @Override
    public void delete(int id) {
        this.accountBusinessRules.isAccountExistById(id);
        this.accountRepository.deleteById(id);
    }
}
