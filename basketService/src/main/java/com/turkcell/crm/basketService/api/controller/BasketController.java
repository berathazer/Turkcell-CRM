package com.turkcell.crm.basketService.api.controller;

import com.turkcell.crm.basketService.business.abstracts.BasketService;
import com.turkcell.crm.basketService.business.dtos.CreateBasketItemRequest;
import com.turkcell.crm.basketService.entity.Basket;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("basketservice/api/v1/baskets/basket")
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;

    @PostMapping("/addItems")
    public void addItem(@RequestBody CreateBasketItemRequest createBasketItemRequests){
        this.basketService.add(createBasketItemRequests);
    }

    @GetMapping
    public Map<String,Basket> getAllItems(){
        return this.basketService.getAllItems();
    }
}
