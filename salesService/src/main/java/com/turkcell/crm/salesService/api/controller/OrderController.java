package com.turkcell.crm.salesService.api.controller;

import com.turkcell.crm.salesService.business.abstracts.OrderService;
import com.turkcell.crm.salesService.entities.Order;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesservice/api/v1/orders/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/add")
    public void add(@RequestParam int accountId){
        this.orderService.add(accountId);

    }

    @GetMapping("/getAll")
    public List<Order> getAll(){
        return this.orderService.getAll();
    }


}
