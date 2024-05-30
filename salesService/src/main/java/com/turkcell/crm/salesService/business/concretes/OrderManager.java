package com.turkcell.crm.salesService.business.concretes;

import com.turkcell.crm.salesService.api.client.BasketClient;
import com.turkcell.crm.salesService.business.abstracts.OrderService;
import com.turkcell.crm.salesService.business.dto.CreateOrderRequestByAccountId;
import com.turkcell.crm.salesService.dataAccess.OrderRepository;
import com.turkcell.crm.salesService.entities.Order;
import com.turkcell.crm.salesService.entities.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {

    private BasketClient basketClient;
    private OrderRepository orderRepository;

    @Override
    public void add(int accountId) {

        CreateOrderRequestByAccountId createOrderRequestByAccountId = this.basketClient.getById(accountId);
        Order order = new Order();
        order.setAccountId(createOrderRequestByAccountId.getAccountId());
        order.setTotalPrice(createOrderRequestByAccountId.getTotalPrice());

        List<OrderItem> orderItemList = new ArrayList<>();
        for(OrderItem orderItem : createOrderRequestByAccountId.getOrderItems()){
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setProductId(orderItem.getProductId());
            orderItem1.setProductName(orderItem.getProductName());

            orderItemList.add(orderItem1);
        }

        order.setOrderItems(orderItemList);

        this.orderRepository.save(order);

    }

    @Override
    public List<Order> getAll() {

        return this.orderRepository.findAll();
    }

}


