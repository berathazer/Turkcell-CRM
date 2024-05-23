package com.turkcell.crm.catalogService.business.concretes;

import com.turkcell.crm.catalogService.business.abstracts.ProductService;
import com.turkcell.crm.catalogService.business.dtos.request.product.CreateProductRequest;
import com.turkcell.crm.catalogService.business.dtos.request.product.UpdateProductRequest;
import com.turkcell.crm.catalogService.business.dtos.response.product.CreatedProductResponse;
import com.turkcell.crm.catalogService.business.dtos.response.product.UpdatedProductResponse;
import com.turkcell.crm.catalogService.business.rules.ProductBusinessRules;
import com.turkcell.crm.catalogService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.catalogService.dataAccess.ProductRepository;
import com.turkcell.crm.catalogService.entity.Product;
import com.turkcell.crm.catalogService.kafka.producers.ProductProducer;
import com.turkcell.turkcellcrm.common.events.product.ProductCreatedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductUpdatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    ModelMapperService modelMapperService;

    @Mock
    ProductBusinessRules productBusinessRules;

    @Mock
    ProductProducer productProducer;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductManager productManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
    }

    @Test
    void add() {

        Product product = new Product();
        product.setId(0);
        product.setName("Iphone");

        Product savedProduct = new Product();
        savedProduct.setId(1);
        savedProduct.setName("Iphone");

        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setName("Iphone");

        //when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(createProductRequest, Product.class)).thenReturn(product);

        when(productRepository.save(product)).thenReturn(savedProduct);

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        productCreatedEvent.setProductId(1);
        productCreatedEvent.setName("Iphone");

        when(modelMapper.map(savedProduct,ProductCreatedEvent.class)).thenReturn(productCreatedEvent);
        //when(modelMapperService.forResponse()).thenReturn(modelMapper);

        CreatedProductResponse createdProductResponse = new CreatedProductResponse();
        createdProductResponse.setId(1);
        createdProductResponse.setName("Iphone");

        when(modelMapper.map(savedProduct,CreatedProductResponse.class)).thenReturn(createdProductResponse);

        CreatedProductResponse result = productManager.add(createProductRequest);
        assertEquals(createdProductResponse, result);

        verify(modelMapperService, times(2)).forRequest();
        verify(modelMapper).map(createProductRequest, Product.class);
        verify(productRepository).save(product);
        verify(modelMapper).map(savedProduct, ProductCreatedEvent.class);
        verify(productProducer).sendCreatedMessage(productCreatedEvent);
        verify(modelMapperService).forResponse();
        verify(modelMapper).map(savedProduct, CreatedProductResponse.class);

    }

    @Test
    void update() {




    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void delete() {
    }
}