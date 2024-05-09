package com.turkcell.turkcellcrm.customerService.api.controller;

import com.turkcell.turkcellcrm.customerService.business.abstracts.AccountTypeService;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.accountType.CreateAccountTypeRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.accountType.UpdateAccountTypeRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.accountType.CreatedAccountTypeResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.accountType.GetAllAccountTypeResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.accountType.GetByIdAccountTypeResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.accountType.UpdatedAccountTypeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerservice/api/v1/accounttypes")
@AllArgsConstructor
public class AccountTypeController {
    private AccountTypeService accountTypeService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountTypeResponse add(@Valid @RequestBody CreateAccountTypeRequest createAccountTypeRequest){
        return this.accountTypeService.add(createAccountTypeRequest);
    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllAccountTypeResponse> getAll(){
        return this.accountTypeService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdAccountTypeResponse getById(@PathVariable int id){
        return this.accountTypeService.getById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedAccountTypeResponse update(@Valid @RequestBody UpdateAccountTypeRequest updateAccountTypeRequest){
        return this.accountTypeService.update(updateAccountTypeRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        this.accountTypeService.delete(id);
    }

}
