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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountManagerTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AccountBusinessRules accountBusinessRules;

    @Mock
    private AccountTypeService accountTypeService;

    @InjectMocks
    private AccountManager accountManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();

        Account account = new Account();

        CreatedAccountResponse createdAccountResponse = new CreatedAccountResponse();

        when(modelMapperService.forRequest()).thenReturn(modelMapper);

        when(modelMapperService.forRequest().map(createAccountRequest, Account.class)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapperService.forResponse().map(account, CreatedAccountResponse.class)).thenReturn(createdAccountResponse);

        CreatedAccountResponse result = accountManager.add(createAccountRequest);

        verify(accountTypeService).getById(createAccountRequest.getAccountTypeId());
        verify(accountBusinessRules).isCustomerExistById(createAccountRequest.getCustomerId());
        verify(accountRepository).save(account);
        assertEquals(createdAccountResponse, result);
    }

    @Test
    void testUpdate() {
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();

        Account account = new Account();

        UpdatedAccountResponse updatedAccountResponse = new UpdatedAccountResponse();
        when(modelMapperService.forRequest()).thenReturn(modelMapper);

        when(modelMapperService.forRequest().map(updateAccountRequest, Account.class)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(modelMapperService.forResponse()).thenReturn(modelMapper);

        when(modelMapperService.forResponse().map(account, UpdatedAccountResponse.class)).thenReturn(updatedAccountResponse);

        UpdatedAccountResponse result = accountManager.update(updateAccountRequest);

        verify(accountBusinessRules).isAccountExistById(updateAccountRequest.getId());
        verify(accountBusinessRules).isAccountAlreadyDeleted(updateAccountRequest.getId());
        verify(accountRepository).save(account);
        assertEquals(updatedAccountResponse, result);
    }

    @Test
    void testGetById() {
        int accountId = 1;
        Account account = new Account();

        GetByIdAccountResponse getByIdAccountResponse = new GetByIdAccountResponse();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapperService.forResponse().map(account, GetByIdAccountResponse.class)).thenReturn(getByIdAccountResponse);

        GetByIdAccountResponse result = accountManager.getById(accountId);

        verify(accountBusinessRules).isAccountAlreadyDeleted(accountId);
        verify(accountBusinessRules).isAccountExistById(accountId);
        assertEquals(getByIdAccountResponse, result);
    }

    @Test
    void testGetAll() {
        List<Account> accounts = List.of(new Account());

        List<GetAllAccountResponse> getAllAccountResponses = List.of(new GetAllAccountResponse());

        when(accountRepository.findByDeletedDateIsNull()).thenReturn(accounts);
        when(modelMapperService.forResponse()).thenReturn(modelMapper);

        when(modelMapperService.forResponse().map(any(Account.class), eq(GetAllAccountResponse.class)))
                .thenAnswer(invocation -> {
                    Account account = invocation.getArgument(0);

                    GetAllAccountResponse response = new GetAllAccountResponse();
                    return response;
                });

        List<GetAllAccountResponse> result = accountManager.getAll();

        verify(accountRepository).findByDeletedDateIsNull();
        assertEquals(getAllAccountResponses, result);
    }

    @Test
    void testDelete() {
        int accountId = 1;
        Account account = new Account();

        when(accountBusinessRules.isAccountAlreadyDeleted(accountId)).thenReturn(account);

        accountManager.delete(accountId);

        verify(accountBusinessRules).isAccountAlreadyDeleted(accountId);
        verify(accountRepository).save(account);
        assertNotNull(account.getDeletedDate());
    }
}



