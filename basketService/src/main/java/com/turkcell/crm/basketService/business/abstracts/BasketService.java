package com.turkcell.crm.basketService.business.abstracts;

import com.turkcell.crm.basketService.business.dtos.CreateBasketItemRequest;
import com.turkcell.crm.basketService.entity.Basket;

import java.util.List;
import java.util.Map;

public interface BasketService {
    void add(CreateBasketItemRequest createBasketItemRequest);
    Map<String,Basket> getAllItems();
}
