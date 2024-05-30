package com.turkcell.crm.salesService.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemDto {
    private int productId;
    private String name;
    private double price;
}
