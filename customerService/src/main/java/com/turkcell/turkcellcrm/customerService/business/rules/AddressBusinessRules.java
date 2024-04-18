package com.turkcell.turkcellcrm.customerService.business.rules;

import com.turkcell.turkcellcrm.customerService.business.dtos.request.address.CreateAddressRequest;
import com.turkcell.turkcellcrm.customerService.business.messages.AddressMessages;
import com.turkcell.turkcellcrm.customerService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.customerService.dataAccess.AddressRepository;
import com.turkcell.turkcellcrm.customerService.entity.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AddressBusinessRules {
    private AddressRepository addressRepository;

    public void isCustomerExist(CreateAddressRequest createAddressRequest){
        Optional<Address> address =this.addressRepository.findByCustomerId(createAddressRequest.getCustomerId());
        if (address.isEmpty()){
            throw new BusinessException(AddressMessages.CUSTOMER_DOES_NOT_EXIST);
        }
    }
}
