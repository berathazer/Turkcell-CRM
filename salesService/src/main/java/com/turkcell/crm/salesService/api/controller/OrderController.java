package com.turkcell.crm.salesService.api.controller;

import com.turkcell.crm.salesService.business.abstracts.OrderService;
import com.turkcell.crm.salesService.business.dto.request.CreateOrderRequestByAccountId;
import com.turkcell.crm.salesService.business.dto.response.GetAllOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesservice/api/v1/orders/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("/add")
    public void add(@RequestBody CreateOrderRequest createOrderRequest){
        this.orderService.add(createOrderRequest);
    }

    @GetMapping("/getAll")
    public List<GetAllOrderResponse>  getAll(){
        return this.orderService.getAll();
    }

}
