package com.turkcell.crm.basketService.business.abstracts;

import com.turkcell.crm.basketService.business.dtos.CreateBasketItemRequest;
import com.turkcell.crm.basketService.business.dtos.CreateOrderRequestByAccountId;
import com.turkcell.crm.basketService.entity.Basket;

import java.util.List;
import java.util.Map;

public interface BasketService {
    void add(CreateBasketItemRequest createBasketItemRequests);
    Map<String,Basket> getAllItems();
    void delete (String id);
    void deleteItem(int productId, int accountId);
    void basketToOrder(int accountId);
}
