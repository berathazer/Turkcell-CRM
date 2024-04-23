package com.turkcell.turkcellcrm.customerService.business.concretes;

import com.turkcell.turkcellcrm.customerService.business.abstracts.AccountService;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.account.CreateAccountRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.account.UpdateAccountRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.CreatedAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.GetAllAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.GetByIdAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.UpdatedAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.rules.AccountBusinessRules;
import com.turkcell.turkcellcrm.customerService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.customerService.dataAccess.AccountRepository;
import com.turkcell.turkcellcrm.customerService.entity.Account;
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
        this.accountBusinessRules.isCustomerExist(createAccountRequest);
        this.accountBusinessRules.isAccountTypeExist(createAccountRequest);
        this.accountBusinessRules.isAddressExist(createAccountRequest);

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
        return this.modelMapperService.forResponse().
                map(this.accountRepository.save(this.modelMapperService.forRequest().
                        map(updateAccountRequest, Account.class)), UpdatedAccountResponse.class);
    }

    @Override
    public void delete(int id) {
        this.accountRepository.deleteById(id);
    }
}
