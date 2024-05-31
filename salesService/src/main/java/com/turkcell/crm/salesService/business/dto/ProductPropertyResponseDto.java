package com.turkcell.crm.salesService.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPropertyResponseDto {
    private String key;
    private String value;
}
