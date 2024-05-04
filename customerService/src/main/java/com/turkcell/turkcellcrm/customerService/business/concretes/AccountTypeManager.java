package com.turkcell.turkcellcrm.customerService.business.concretes;

import com.turkcell.turkcellcrm.customerService.business.abstracts.AccountTypeService;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.accountType.CreateAccountTypeRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.accountType.UpdateAccountTypeRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.accountType.CreatedAccountTypeResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.accountType.GetAllAccountTypeResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.accountType.GetByIdAccountTypeResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.accountType.UpdatedAccountTypeResponse;
import com.turkcell.turkcellcrm.customerService.business.rules.AccountTypeBusinessRules;
import com.turkcell.turkcellcrm.customerService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.customerService.dataAccess.AccountTypeRepository;
import com.turkcell.turkcellcrm.customerService.entity.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AccountTypeManager implements AccountTypeService {
    private AccountTypeRepository accountTypeRepository;
    private ModelMapperService modelMapperService;
    private AccountTypeBusinessRules accountTypeBusinessRules;
    @Override
    public CreatedAccountTypeResponse add(CreateAccountTypeRequest createAccountTypeRequest) {
        AccountType accountType =this.modelMapperService.forRequest().map(createAccountTypeRequest, AccountType.class);
        this.accountTypeRepository.save(accountType);

        return this.modelMapperService.forResponse().map(accountType, CreatedAccountTypeResponse.class);
    }

    @Override
    public List<GetAllAccountTypeResponse> getAll() {
        List<AccountType> accountType = this.accountTypeRepository.findAll();
        return accountType.stream().map(accountType1 -> this.modelMapperService.forResponse().
                map(accountType1, GetAllAccountTypeResponse.class)).toList();
    }

    @Override
    public GetByIdAccountTypeResponse getById(int id) {
        this.accountTypeBusinessRules.isAccountTypeExistById(id);
        return this.modelMapperService.forResponse()
                .map(this.accountTypeRepository.findById(id), GetByIdAccountTypeResponse.class);
    }

    @Override
    public UpdatedAccountTypeResponse update(UpdateAccountTypeRequest updateAccountTypeRequest) {
        this.accountTypeBusinessRules.isAccountTypeExistById(updateAccountTypeRequest.getId());
        return this.modelMapperService.forResponse().
                map(this.accountTypeRepository.save(this.modelMapperService.forRequest().
                        map(updateAccountTypeRequest, AccountType.class)), UpdatedAccountTypeResponse.class);
    }

    @Override
    public void delete(int id) {
        this.accountTypeBusinessRules.isAccountTypeExistById(id);
        this.accountTypeRepository.deleteById(id);
    }
}
