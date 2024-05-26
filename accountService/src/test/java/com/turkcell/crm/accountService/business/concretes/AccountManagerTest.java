package com.turkcell.crm.accountService.business.concretes;

import com.turkcell.crm.accountService.business.abstracts.AccountTypeService;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.xml.catalog.Catalog;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountManagerTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private AccountBusinessRules accountBusinessRules;

    @Mock
    private AccountTypeService accountTypeService;

    @InjectMocks
    private AccountManager accountManager;

    private Account account;
    private CreateAccountRequest createAccountRequest;
    private CreatedAccountResponse createdAccountResponse;
    private UpdateAccountRequest updateAccountRequest;
    private UpdatedAccountResponse updatedAccountResponse;
    private GetByIdAccountResponse getByIdAccountResponse;
    private GetAllAccountResponse getAllAccountResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        account = new Account();
        account.setId(1);
        account.setCustomerId(1);
       // account.setAccountTypeId(1);
        account.setCreatedDate(LocalDateTime.now());

        createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setCustomerId(1);
        createAccountRequest.setAccountTypeId(1);

        createdAccountResponse = new CreatedAccountResponse();
        createdAccountResponse.setId(1);

        updateAccountRequest = new UpdateAccountRequest();
        updateAccountRequest.setId(1);

        updatedAccountResponse = new UpdatedAccountResponse();
        updatedAccountResponse.setId(1);

        getByIdAccountResponse = new GetByIdAccountResponse();
        getByIdAccountResponse.setId(1);

        getAllAccountResponse = new GetAllAccountResponse();
        getAllAccountResponse.setId(1);
    }

    @Test
    void add_shouldAddAccount() {
        when(accountTypeService.getById(createAccountRequest.getAccountTypeId())).thenReturn(any());
        doNothing().when(accountBusinessRules).isCustomerExistById(createAccountRequest.getCustomerId());
        when(modelMapperService.forRequest().map(createAccountRequest, Account.class)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(modelMapperService.forResponse().map(account, CreatedAccountResponse.class)).thenReturn(createdAccountResponse);

        CreatedAccountResponse result = accountManager.add(createAccountRequest);

        assertNotNull(result);
        assertEquals(createdAccountResponse.getId(), result.getId());
        verify(accountTypeService).getById(createAccountRequest.getAccountTypeId());
        verify(accountBusinessRules).isCustomerExistById(createAccountRequest.getCustomerId());
        verify(accountRepository).save(account);
    }

    @Test
    void getById_shouldReturnAccountById() {
        doNothing().when(accountBusinessRules).isAccountAlreadyDeleted(account.getId());
        doNothing().when(accountBusinessRules).isAccountExistById(account.getId());
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        when(modelMapperService.forResponse().map(any(Account.class), eq(GetByIdAccountResponse.class))).thenReturn(getByIdAccountResponse);

        GetByIdAccountResponse result = accountManager.getById(account.getId());

        assertNotNull(result);
        assertEquals(getByIdAccountResponse.getId(), result.getId());
        verify(accountBusinessRules).isAccountAlreadyDeleted(account.getId());
        verify(accountBusinessRules).isAccountExistById(account.getId());
        verify(accountRepository).findById(account.getId());
    }

    @Test
    void getAll_shouldReturnAllAccounts() {
        when(accountRepository.findByDeletedDateIsNull()).thenReturn(Collections.singletonList(account));
        when(modelMapperService.forResponse().map(account, GetAllAccountResponse.class)).thenReturn(getAllAccountResponse);

        List<GetAllAccountResponse> result = accountManager.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(accountRepository).findByDeletedDateIsNull();
    }

    @Test
    void update_shouldUpdateAccount() {
        doNothing().when(accountBusinessRules).isAccountExistById(updateAccountRequest.getId());
        doNothing().when(accountBusinessRules).isAccountAlreadyDeleted(updateAccountRequest.getId());
        when(modelMapperService.forRequest().map(updateAccountRequest, Account.class)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(modelMapperService.forResponse().map(account, UpdatedAccountResponse.class)).thenReturn(updatedAccountResponse);

        UpdatedAccountResponse result = accountManager.update(updateAccountRequest);

        assertNotNull(result);
        assertEquals(updatedAccountResponse.getId(), result.getId());
        verify(accountBusinessRules).isAccountExistById(updateAccountRequest.getId());
        verify(accountBusinessRules).isAccountAlreadyDeleted(updateAccountRequest.getId());
        verify(accountRepository).save(account);
    }

    @Test
    void delete_shouldDeleteAccount() {
        doNothing().when(accountBusinessRules).isAccountAlreadyDeleted(account.getId());
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        accountManager.delete(account.getId());

        assertNotNull(account.getDeletedDate());
        verify(accountBusinessRules).isAccountAlreadyDeleted(account.getId());
        verify(accountRepository).save(account);
    }
}