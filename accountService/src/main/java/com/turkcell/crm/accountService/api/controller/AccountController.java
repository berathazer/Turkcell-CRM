package com.turkcell.crm.accountService.api.controller;



import com.turkcell.crm.accountService.business.abstracts.AccountService;
import com.turkcell.crm.accountService.business.dtos.request.account.CreateAccountRequest;
import com.turkcell.crm.accountService.business.dtos.request.account.UpdateAccountRequest;
import com.turkcell.crm.accountService.business.dtos.response.account.CreatedAccountResponse;
import com.turkcell.crm.accountService.business.dtos.response.account.GetAllAccountResponse;
import com.turkcell.crm.accountService.business.dtos.response.account.GetByIdAccountResponse;
import com.turkcell.crm.accountService.business.dtos.response.account.UpdatedAccountResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountservice/api/v1/accounts/account")
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
    public String delete(@PathVariable int id){

        this.accountService.delete(id);

        // TODO: Düzgün formata getir (resources)
        return "Silme başarılı";
    }

}
