package com.turkcell.turkcellcrm.customerService.business.abstracts;

import com.turkcell.turkcellcrm.customerService.business.dtos.request.contact.CreateContactRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.request.contact.UpdateContactRequest;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.CreatedContactResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.GetAllContactResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.GetByIdContactResponse;
import com.turkcell.turkcellcrm.customerService.business.dtos.response.contact.UpdatedContactResponse;

import java.util.List;

public interface ContactService {
    CreatedContactResponse add(CreateContactRequest createContactRequest);
    List<GetAllContactResponse> getAll();
    GetByIdContactResponse getById(int id);
    UpdatedContactResponse update(UpdateContactRequest updateContactRequest);
    void delete(int id);
}
