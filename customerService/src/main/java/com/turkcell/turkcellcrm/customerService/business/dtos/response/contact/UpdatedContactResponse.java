package com.turkcell.turkcellcrm.customerService.business.dtos.response.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdatedContactResponse {
    private int id;
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String fax;
}
