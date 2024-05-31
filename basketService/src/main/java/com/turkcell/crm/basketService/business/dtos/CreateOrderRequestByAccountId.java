package com.turkcell.crm.basketService.business.dtos;

import com.turkcell.crm.basketService.entity.BasketItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestByAccountId {

    private String id;
    private int customerId;
    private int accountId;
    private double totalPrice;
    private List<BasketItemDto> basketItemDtos;

}
