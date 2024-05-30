package com.turkcell.turkcellcrm.searchService.api.client;


import com.turkcell.turkcellcrm.searchService.business.dto.request.CreateBasketItemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "basketService", url = "http://localhost:10033/basketservice/api/v1/baskets/basket")
public interface BasketClient {
    @PostMapping("/addItems")
    void sendProduct(@RequestBody CreateBasketItemRequest createBasketItemRequests);
}






