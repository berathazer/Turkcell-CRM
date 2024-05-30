package com.turkcell.crm.salesService.business.abstracts;

import com.turkcell.crm.salesService.entities.Order;

import java.util.List;

public interface OrderService {
    void add(int accountId);
    List<Order> getAll();
}
