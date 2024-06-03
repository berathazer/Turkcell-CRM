package com.turkcell.crm.salesService.business.concretes;

import com.turkcell.crm.salesService.api.client.CatalogClient;
import com.turkcell.crm.salesService.api.client.CustomerClient;
import com.turkcell.crm.salesService.business.dto.response.GetAllOrderResponse;
import com.turkcell.crm.salesService.business.dto.response.GetAllProductConfigResponse;
import com.turkcell.crm.salesService.business.dto.response.ProductPropertyResponseDto;
import com.turkcell.crm.salesService.core.mapping.ModelMapperService;
import com.turkcell.crm.salesService.dataAccess.OrderRepository;
import com.turkcell.crm.salesService.entities.Order;
import com.turkcell.crm.salesService.entities.OrderItem;
import com.turkcell.crm.salesService.entities.ProductConfig;
import com.turkcell.crm.salesService.kafka.producers.OrderProducer;
import com.turkcell.turkcellcrm.common.events.basket.BasketItemDto;
import com.turkcell.turkcellcrm.common.events.basket.CreateOrderRequest;
import com.turkcell.turkcellcrm.common.events.order.OrderCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderManagerTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerClient customerClient;

    @Mock
    private CatalogClient catalogClient;

    @Mock
    private OrderProducer orderProducer;

    @Mock
    private ModelMapper modelMapper ;

    @Mock
    private ModelMapperService modelMapperService;

    @InjectMocks
    private OrderManager orderManager;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setCustomerId(1);
        BasketItemDto basketItemDto = new BasketItemDto();
        basketItemDto.setProductId(1);
        basketItemDto.setName("Product 1");
        createOrderRequest.setBasketItemDtos(Collections.singletonList(basketItemDto));

        Order order = new Order();
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(createOrderRequest, Order.class)).thenReturn(order);
        when(customerClient.getAddressIdByCustomerId(1)).thenReturn(1);


        orderManager.add(createOrderRequest);

        verify(orderRepository).save(any(Order.class));
        verify(orderProducer).sendCreatedMessage(any(OrderCreatedEvent.class));
        verify(modelMapperService.forRequest(), times(1)).map(any(), eq(Order.class));
    }

    @Test
    void testGetAll() {
        Order order = new Order();
        List<Order> orders = Collections.singletonList(order);
        when(orderRepository.findAll()).thenReturn(orders);

        GetAllOrderResponse getAllOrderResponse = new GetAllOrderResponse();
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapper.map(order, GetAllOrderResponse.class)).thenReturn(getAllOrderResponse);

        List<GetAllOrderResponse> result = orderManager.getAll();

        assertEquals(1, result.size());
        verify(orderRepository).findAll();
    }

    @Test
    void testGetAllProductConfig() {
        // Arrange
        int accountId = 1;
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1);
        order.setOrderItems(Collections.singletonList(orderItem));
        when(orderRepository.findOrderByAccountId(accountId)).thenReturn(order);

        ProductPropertyResponseDto productPropertyResponseDto = new ProductPropertyResponseDto();
        when(catalogClient.getProductPropertyIdByProductId(anyList())).thenReturn(Collections.singletonList(productPropertyResponseDto));

        ProductConfig productConfig = new ProductConfig();
        when(modelMapper.map(productPropertyResponseDto, ProductConfig.class)).thenReturn(productConfig);

        GetAllProductConfigResponse getAllProductConfigResponse = new GetAllProductConfigResponse();
        when(modelMapper.map(productConfig, GetAllProductConfigResponse.class)).thenReturn(getAllProductConfigResponse);

        // Act
        List<GetAllProductConfigResponse> result = orderManager.getAllProductConfig(accountId);

        // Assert
        assertEquals(1, result.size());
        verify(orderRepository).findOrderByAccountId(accountId);
        verify(catalogClient).getProductPropertyIdByProductId(anyList());
    }

    @Test
    void testSetOrderItemList() {

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        BasketItemDto basketItemDto = new BasketItemDto();
        basketItemDto.setProductId(1);
        basketItemDto.setName("Product 1");
        createOrderRequest.setBasketItemDtos(Collections.singletonList(basketItemDto));

        List<OrderItem> orderItems = orderManager.setOrderItemList(createOrderRequest);

        assertEquals(1, orderItems.size());
        assertEquals(1, orderItems.get(0).getProductId());
        assertEquals("Product 1", orderItems.get(0).getProductName());
    }
}