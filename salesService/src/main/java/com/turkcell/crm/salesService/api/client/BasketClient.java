package com.turkcell.crm.salesService.api.client;

import com.turkcell.crm.salesService.business.dto.CreateOrderRequestByAccountId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "basketService", url = "http://localhost:10033/basketservice/api/v1/baskets/basket")
public interface BasketClient {
    @GetMapping("/getById/{accountId}")
    CreateOrderRequestByAccountId getById(@PathVariable int accountId);
}
