package com.turkcell.crm.salesService.api.controller;

import com.turkcell.crm.salesService.business.abstracts.OrderService;
import com.turkcell.crm.salesService.entities.Order;
import com.turkcell.crm.salesService.entities.ProductConfig;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salesservice/api/v1/orders/productproperties")
@AllArgsConstructor
public class ProductConfigurationController {

    private OrderService orderService;

    @GetMapping("/getAll/{accountId}")
    public List<ProductConfig> getAll(@PathVariable int accountId){
        return this.orderService.getAllProductConfig(accountId);
    }
}
