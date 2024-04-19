package com.turkcell.turkcellcrm.customerService.business.rules;

import com.turkcell.turkcellcrm.customerService.business.dtos.request.contact.CreateContactRequest;
import com.turkcell.turkcellcrm.customerService.business.messages.ContactMessages;
import com.turkcell.turkcellcrm.customerService.core.business.abstracts.MessageService;
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
    private MessageService messageService;

    public void isCustomerExist(CreateContactRequest createContactRequest) {
        Optional<Contact> contact = this.contactRepository.findByCustomerId(createContactRequest.getCustomerId());
        if (contact.isPresent()) {
            throw new BusinessException(messageService.getMessage(ContactMessages.CONTACT_CUSTOMER_DOES_NOT_EXIST));
        }
    }
}
