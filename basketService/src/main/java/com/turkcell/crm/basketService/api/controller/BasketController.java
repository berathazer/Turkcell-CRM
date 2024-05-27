package com.turkcell.crm.basketService.api.controller;

import com.turkcell.crm.basketService.business.abstracts.BasketService;
import com.turkcell.crm.basketService.entity.Basket;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("basketservice/api/v1/baskets/basket")
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;

    @PostMapping
    public void addItem(@RequestParam String customerId, @RequestParam String productId){
        basketService.add(customerId,productId);
    }

    @GetMapping
    public Map<String, Basket> getAllItems(){
        return basketService.getAllItems();
    }
}
