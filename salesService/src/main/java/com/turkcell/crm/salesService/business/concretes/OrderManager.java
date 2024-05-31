package com.turkcell.crm.salesService.business.concretes;


import com.turkcell.crm.salesService.api.client.CustomerClient;
import com.turkcell.crm.salesService.business.abstracts.OrderService;
import com.turkcell.crm.salesService.business.dto.BasketItemDto;
import com.turkcell.crm.salesService.business.dto.CreateOrderRequestByAccountId;
import com.turkcell.crm.salesService.dataAccess.OrderRepository;
import com.turkcell.crm.salesService.entities.Order;

import com.turkcell.crm.salesService.entities.OrderItemEntity;
import com.turkcell.crm.salesService.entities.ProductConfig;
import com.turkcell.crm.salesService.kafka.producers.OrderProducer;
import com.turkcell.turkcellcrm.common.events.order.OrderCreatedEvent;

import com.turkcell.turkcellcrm.common.events.order.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {

    private OrderRepository orderRepository;
    private CustomerClient customerClient;
//    private CatalogClient catalogClient;
    private OrderProducer orderProducer;

    @Override
    @Transactional
    public void add(CreateOrderRequestByAccountId createOrderRequestByAccountId) {

        Order order = new Order();
        order.setAccountId(createOrderRequestByAccountId.getAccountId());
        order.setTotalPrice(createOrderRequestByAccountId.getTotalPrice());
        order.setCustomerId(createOrderRequestByAccountId.getCustomerId());

        List<OrderItemEntity> orderItemList = new ArrayList<>();
        for(BasketItemDto basketItemDto : createOrderRequestByAccountId.getBasketItemDtos()){
            OrderItemEntity orderItem1 = new OrderItemEntity();
            orderItem1.setProductId(basketItemDto.getProductId());
            orderItem1.setProductName(basketItemDto.getName());

            orderItemList.add(orderItem1);
        }

        order.setAddressId(this.customerClient.getAddressIdByCustomerId(createOrderRequestByAccountId.getCustomerId()));
        order.setOrderItemEntities(orderItemList);
        this.orderRepository.save(order);


        OrderCreatedEvent orderCreatedEvent = getOrderCreatedEvent(order);
        this.orderProducer.sendCreatedMessage(orderCreatedEvent);
    }

    private static OrderCreatedEvent getOrderCreatedEvent(Order order) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setAccountId(order.getAccountId());
        orderCreatedEvent.setAddressId(order.getAddressId());
        orderCreatedEvent.setCustomerId(order.getCustomerId());
        orderCreatedEvent.setTotalPrice(order.getTotalPrice());

        List<OrderItem> orderItemEventList = new ArrayList<>();
        for (OrderItemEntity orderItemEntity: order.getOrderItemEntities()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(orderItemEntity.getProductId());
            orderItem.setProductName(orderItemEntity.getProductName());
            orderItemEventList.add(orderItem);
        }


        orderCreatedEvent.setOrderItemList(orderItemEventList);
        return orderCreatedEvent;
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    //TODO: ProductPropertyleri getirirken tek tek değil liste halinde id leri gönder.
    @Override
    public List<ProductConfig> getAllProductConfig(int accountId) {
        Order order = this.orderRepository.findOrderByAccountId(accountId);

        List<OrderItemEntity> orderItems = order.getOrderItemEntities();
        List<ProductConfig> productConfigs = new ArrayList<>();


//        this.catalogClient.getProductPropertyIdByProductId(orderItem.getProductId());
//        this.catalogClient.getProductPropertyIdByProductId();
        return null;
    }
}


