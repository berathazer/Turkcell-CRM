package com.turkcell.turkcellcrm.customerService.business.dtos.request.accountType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateAccountTypeRequest {
    @NotNull
    private int id;
    @NotNull
    @Size(min = 7, max = 50)
    private String accountTypeName;
}
