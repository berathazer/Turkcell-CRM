package com.turkcell.turkcellcrm.customerService.business.abstracts;

import com.turkcell.turkcellcrm.customerService.business.dtos.request.account.CreateAccountRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.account.UpdateAccountRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.CreatedAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.GetAllAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.GetByIdAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.UpdatedAccountResponse;

import java.util.List;

public interface AccountService {
    CreatedAccountResponse add(CreateAccountRequest createAccountRequest);
    GetByIdAccountResponse getById(int id);
    List<GetAllAccountResponse> getAll();
    UpdatedAccountResponse update(UpdateAccountRequest updateAccountRequest);
    void delete(int id);
}
