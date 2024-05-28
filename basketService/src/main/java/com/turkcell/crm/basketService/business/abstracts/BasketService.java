package com.turkcell.crm.basketService.business.abstracts;

import com.turkcell.crm.basketService.business.dtos.GetAllProductResponse;
import com.turkcell.crm.basketService.entity.Basket;
import com.turkcell.crm.basketService.entity.BasketItem;

import java.util.List;
import java.util.Map;

public interface BasketService {
    void add(List<GetAllProductResponse> getAllProductResponses,int accountId);
    List<Basket> getAllItems();
}
