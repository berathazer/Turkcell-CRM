package com.turkcell.turkcellcrm.customerService.business.dtos.response.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllContactResponse {
    private int id;
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String fax;
}
