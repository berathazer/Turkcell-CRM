package com.turkcell.crm.salesService.business.concretes;


import com.turkcell.crm.salesService.api.client.CustomerClient;
import com.turkcell.crm.salesService.business.abstracts.OrderService;
import com.turkcell.crm.salesService.business.dto.response.GetAllOrderResponse;
import com.turkcell.crm.salesService.core.mapping.ModelMapperService;
import com.turkcell.crm.salesService.dataAccess.OrderRepository;
import com.turkcell.crm.salesService.entities.Order;

import com.turkcell.crm.salesService.entities.OrderItem;
import com.turkcell.crm.salesService.entities.ProductConfig;
import com.turkcell.crm.salesService.kafka.producers.OrderProducer;


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
    private ModelMapperService modelMapperService;


    @Override
    @Transactional
    public void add(CreateOrderRequest createOrderRequest) {

        Order order = this.modelMapperService.forResponse().map(createOrderRequest, Order.class);

        int addressId = this.customerClient.getAddressIdByCustomerId(createOrderRequest.getCustomerId());
        order.setAddressId(addressId);

        order.setOrderItemEntities(setOrderItemList(createOrderRequest));
        this.orderRepository.save(order);

        OrderCreatedEvent orderCreatedEvent = this.modelMapperService.forResponse().map(order, OrderCreatedEvent.class);
        this.orderProducer.sendCreatedMessage(orderCreatedEvent);
    }


    @Override
    public List<GetAllOrderResponse> getAll() {

        List<Order> orders = this.orderRepository.findAll();
        return orders.stream().
                map(order -> this.modelMapperService.forResponse().map(order, GetAllOrderResponse.class)).toList();
    }

    //TODO: ProductPropertyleri getirirken tek tek değil liste halinde id leri gönder.
    @Override
    public List<ProductConfig> getAllProductConfig(int accountId) {
        Order order = this.orderRepository.findOrderByAccountId(accountId);

        List<OrderItem> orderItems = order.getOrderItemEntities();
        List<ProductConfig> productConfigs = new ArrayList<>();


//        this.catalogClient.getProductPropertyIdByProductId(orderItem.getProductId());
//        this.catalogClient.getProductPropertyIdByProductId();
        return null;
    }

    public List<OrderItem> setOrderItemList(CreateOrderRequest createOrderRequest){

        List<OrderItem> orderItemList = new ArrayList<>();
        for(BasketItemDto basketItemDto : createOrderRequest.getBasketItemDtos()){
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setProductId(basketItemDto.getProductId());
            orderItem1.setProductName(basketItemDto.getName());

            orderItemList.add(orderItem1);
        }

        return orderItemList;
    }
}


