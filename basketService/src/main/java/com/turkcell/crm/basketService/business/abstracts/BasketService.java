package com.turkcell.crm.basketService.business.abstracts;

import com.turkcell.crm.basketService.entity.Basket;

import java.util.Map;

public interface BasketService {
    void add(String customerId,String productId);
    Map<String, Basket> getAllItems();
}
