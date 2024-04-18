package com.turkcell.turkcellcrm.customerService.business.dtos.request.contact;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateContactRequest {
    @NotNull
    private int customerId;
    @NotNull
    @Size(min = 15 ,max = 30)
    private String email;
    private String homePhone;
    @NotNull
    @Size(min = 10,max = 10)
    private String mobilePhone;
    private String fax;
}
