package com.turkcell.turkcellcrm.customerService.business.rules;

import com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.CreateCustomerRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.UpdateCustomerRequest;
import com.turkcell.turkcellcrm.customerService.business.messages.CustomerMessages;
import com.turkcell.turkcellcrm.customerService.core.business.abstracts.MessageService;
import com.turkcell.turkcellcrm.customerService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.customerService.dataAccess.CustomerRepository;
import com.turkcell.turkcellcrm.customerService.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerBusinessRules {
    private CustomerRepository customerRepository;
    private MessageService messageService;
    public void nationalityNumberCanNotBeDuplicate(CreateCustomerRequest createCustomerRequest){
        Optional<Customer> customer = this.customerRepository.
                findCustomerByNationalityNumberEquals(createCustomerRequest.getNationalityNumber());
        if (customer.isPresent()){
            throw new BusinessException(messageService.getMessage(CustomerMessages.NATIONALITY_ID_CAN_NOT_DUPLICATE));
        }
    }
    public void nationalityNumberCanNotBeDuplicate(UpdateCustomerRequest updateCustomerRequest){
        Optional<Customer> customer = this.customerRepository.
                findCustomerByNationalityNumberEquals(updateCustomerRequest.getNationalityNumber());
        if (customer.isPresent()){
            throw new BusinessException(messageService.getMessage(CustomerMessages.NATIONALITY_ID_CAN_NOT_DUPLICATE));
        }
    }

    public void isCustomerIdExist(int id){
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isEmpty()){
            throw new BusinessException(messageService.getMessage(CustomerMessages.CUSTOMER_DOES_NOT_EXIST));
        }
    }
}
