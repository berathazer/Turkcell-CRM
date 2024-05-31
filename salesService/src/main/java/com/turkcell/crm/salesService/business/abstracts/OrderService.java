package com.turkcell.crm.salesService.business.abstracts;

import com.turkcell.crm.salesService.business.dto.CreateOrderRequestByAccountId;
import com.turkcell.crm.salesService.entities.Order;
import com.turkcell.crm.salesService.entities.ProductConfig;

import java.util.List;

public interface OrderService {
    void add(CreateOrderRequestByAccountId createOrderRequestByAccountId);
    List<Order> getAll();
    List<ProductConfig> getAllProductConfig(int accountId);
}
