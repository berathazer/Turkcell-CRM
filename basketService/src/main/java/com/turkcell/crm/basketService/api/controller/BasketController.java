package com.turkcell.crm.basketService.api.controller;

import com.turkcell.crm.basketService.business.abstracts.BasketService;
import com.turkcell.crm.basketService.business.dtos.GetAllProductResponse;
import com.turkcell.crm.basketService.entity.Basket;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("basketservice/api/v1/baskets/basket")
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;

    @PostMapping
    public void addItem(@RequestBody List<GetAllProductResponse> getAllProductResponses,@RequestParam int accountId){
        this.basketService.add(getAllProductResponses,accountId);
    }

    @GetMapping
    public List<Basket> getAllItems(){
        return this.basketService.getAllItems();
    }
}
