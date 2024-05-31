package com.turkcell.crm.salesService.business.abstracts;

import com.turkcell.crm.salesService.entities.Order;
import com.turkcell.crm.salesService.entities.ProductConfig;

import java.util.List;

public interface OrderService {
    void add(int accountId);
    List<Order> getAll();
    List<ProductConfig> getAllProductConfig(int accountId);
}
