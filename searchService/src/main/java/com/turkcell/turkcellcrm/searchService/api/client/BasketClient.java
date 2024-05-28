package com.turkcell.turkcellcrm.searchService.api.client;


import com.turkcell.turkcellcrm.searchService.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "basketservice", url = "http://localhost:9002/basketservice/api/v1/baskets/basket")
public interface BasketClient {
    @PostMapping("/addItem")
    void sendProduct(List<Product> products, int accountId);
}






