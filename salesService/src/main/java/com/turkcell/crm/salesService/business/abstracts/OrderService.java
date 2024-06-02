package com.turkcell.crm.salesService.business.abstracts;


import com.turkcell.crm.salesService.business.dto.response.GetAllOrderResponse;
import com.turkcell.crm.salesService.entities.ProductConfig;

import java.util.List;

public interface OrderService {
    void add(CreateOrderRequest createOrderRequest);
    List<GetAllOrderResponse>  getAll();
    List<ProductConfig> getAllProductConfig(int accountId);
}
