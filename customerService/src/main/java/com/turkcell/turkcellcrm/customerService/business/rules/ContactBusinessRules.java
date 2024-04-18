package com.turkcell.turkcellcrm.customerService.business.rules;

import com.turkcell.turkcellcrm.customerService.business.dtos.request.contact.CreateContactRequest;
import com.turkcell.turkcellcrm.customerService.business.messages.ContactMessages;
import com.turkcell.turkcellcrm.customerService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.customerService.dataAccess.ContactRepository;
import com.turkcell.turkcellcrm.customerService.entity.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactBusinessRules {
    private ContactRepository contactRepository;

    public void isCustomerExist(CreateContactRequest createContactRequest) {
        Optional<Contact> contact = this.contactRepository.findByCustomerId(createContactRequest.getCustomerId());
        if (contact.isEmpty()) {
            throw new BusinessException(ContactMessages.CUSTOMER_DOES_NOT_EXIST);
        }
    }
}
