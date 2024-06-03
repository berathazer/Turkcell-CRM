package com.turkcell.crm.salesService.business.abstracts;


import com.turkcell.crm.salesService.business.dto.response.GetAllOrderResponse;
import com.turkcell.crm.salesService.business.dto.response.GetAllProductConfigResponse;
import com.turkcell.crm.salesService.business.dto.response.GetByIdOrderResponse;
import com.turkcell.crm.salesService.entities.Order;
import com.turkcell.crm.salesService.entities.ProductConfig;
import com.turkcell.turkcellcrm.common.events.basket.CreateOrderRequest;

import java.util.List;

public interface OrderService {
    void add(CreateOrderRequest createOrderRequest);
    List<GetAllOrderResponse>  getAll();
    List<GetAllProductConfigResponse> getAllProductConfig(int accountId);
    GetByIdOrderResponse getById(String orderId);
}
