package com.turkcell.crm.salesService.business.concretes;

import com.turkcell.crm.salesService.api.client.BasketClient;
import com.turkcell.crm.salesService.api.client.CatalogClient;
import com.turkcell.crm.salesService.api.client.CustomerClient;
import com.turkcell.crm.salesService.business.abstracts.OrderService;
import com.turkcell.crm.salesService.business.dto.BasketItemDto;
import com.turkcell.crm.salesService.business.dto.CreateOrderRequestByAccountId;
import com.turkcell.crm.salesService.dataAccess.OrderRepository;
import com.turkcell.crm.salesService.entities.Order;
import com.turkcell.crm.salesService.entities.OrderItem;
import com.turkcell.crm.salesService.entities.ProductConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {

    private BasketClient basketClient;
    private OrderRepository orderRepository;
    private CustomerClient customerClient;
    private CatalogClient catalogClient;

    @Override
    @Transactional
    public void add(int accountId) {

        CreateOrderRequestByAccountId createOrderRequestByAccountId = this.basketClient.getById(accountId);

        Order order = new Order();
        order.setAccountId(createOrderRequestByAccountId.getAccountId());
        order.setTotalPrice(createOrderRequestByAccountId.getTotalPrice());
        order.setCustomerId(createOrderRequestByAccountId.getCustomerId());

        List<OrderItem> orderItemList = new ArrayList<>();
        for(BasketItemDto basketItemDto : createOrderRequestByAccountId.getBasketItemDtos()){
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setProductId(basketItemDto.getProductId());
            orderItem1.setProductName(basketItemDto.getName());

            orderItemList.add(orderItem1);
        }

        order.setAddressId(this.customerClient.getAddressIdByCustomerId(createOrderRequestByAccountId.getCustomerId()));
        order.setOrderItems(orderItemList);
        this.orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    //TODO: ProductPropertyleri getirirken tek tek değil liste halinde id leri gönder.
    @Override
    public List<ProductConfig> getAllProductConfig(int accountId) {
        Order order = this.orderRepository.findOrderByAccountId(accountId);

        List<OrderItem> orderItems = order.getOrderItems();
        List<ProductConfig> productConfigs = new ArrayList<>();


//        this.catalogClient.getProductPropertyIdByProductId(orderItem.getProductId());
//        this.catalogClient.getProductPropertyIdByProductId();
        return null;
    }
}


