package com.turkcell.crm.salesService.business.dto;

import com.turkcell.crm.salesService.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderRequestByAccountId {
    private String id;
    private int accountId;
    private double totalPrice;
    private List<OrderItem> orderItems;
}
