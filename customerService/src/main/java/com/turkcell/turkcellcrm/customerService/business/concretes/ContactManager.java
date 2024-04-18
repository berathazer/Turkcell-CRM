package com.turkcell.turkcellcrm.customerService.business.concretes;

import com.turkcell.turkcellcrm.customerService.business.abstracts.ContactService;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.contact.CreateContactRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.contact.UpdateContactRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.CreatedContactResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.GetAllContactResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.GetByIdContactResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.UpdatedContactResponse;
import com.turkcell.turkcellcrm.customerService.business.rules.ContactBusinessRules;
import com.turkcell.turkcellcrm.customerService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.customerService.dataAccess.ContactRepository;
import com.turkcell.turkcellcrm.customerService.entity.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactManager implements ContactService {
    private ContactRepository contactRepository;
    private ModelMapperService modelMapperService;
    private ContactBusinessRules contactBusinessRules;
    @Override
    public CreatedContactResponse add(CreateContactRequest createContactRequest) {
        this.contactBusinessRules.isCustomerExist(createContactRequest);

        Contact contact = this.modelMapperService.forRequest().map(createContactRequest, Contact.class);
        CreatedContactResponse createdContactResponse = this.modelMapperService.forResponse().
                map(this.contactRepository.save(contact), CreatedContactResponse.class);
        return createdContactResponse;
    }

    @Override
    public List<GetAllContactResponse> getAll() {
        List<Contact> contacts = this.contactRepository.findAll();
        List<GetAllContactResponse> getAllContactResponses = contacts.stream().map(contact -> this.modelMapperService.forResponse().
                map(contact, GetAllContactResponse.class)).toList();
        return getAllContactResponses;
    }

    @Override
    public GetByIdContactResponse getById(int id) {
        Optional<Contact> contact =this.contactRepository.findById(id);
        return this.modelMapperService.forResponse().map(contact, GetByIdContactResponse.class);
    }

    @Override
    public UpdatedContactResponse update(UpdateContactRequest updateContactRequest) {

        Contact contact = this.modelMapperService.forRequest().map(updateContactRequest,Contact.class);
        return this.modelMapperService.forResponse().
                map(this.contactRepository.save(contact), UpdatedContactResponse.class);
    }

    @Override
    public void delete(int id) {
        this.contactRepository.deleteById(id);
    }
}
