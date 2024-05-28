package com.turkcell.crm.basketService.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductResponse {
    private String name;
    private int productId;
    private double price;
    private int accountId;
}