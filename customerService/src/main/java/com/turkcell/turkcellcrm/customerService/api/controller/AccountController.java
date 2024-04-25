package com.turkcell.turkcellcrm.customerService.api.controller;

import com.turkcell.turkcellcrm.customerService.business.abstracts.AccountService;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.account.CreateAccountRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.account.UpdateAccountRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.CreatedAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.GetAllAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.GetByIdAccountResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.account.UpdatedAccountResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerservice/api/v1/accounts")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountResponse add(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        return this.accountService.add(createAccountRequest);
    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllAccountResponse> getAll(){
        return this.accountService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdAccountResponse getById(@PathVariable int id){
        return this.accountService.getById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedAccountResponse update(@Valid @RequestBody UpdateAccountRequest updateAccountRequest){
        return this.accountService.update(updateAccountRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        this.accountService.delete(id);
    }

}
