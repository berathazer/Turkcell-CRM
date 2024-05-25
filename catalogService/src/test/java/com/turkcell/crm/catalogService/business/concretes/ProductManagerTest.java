package com.turkcell.crm.catalogService.business.concretes;

import com.turkcell.crm.catalogService.business.abstracts.ProductService;
import com.turkcell.crm.catalogService.business.dtos.request.product.CreateProductRequest;
import com.turkcell.crm.catalogService.business.dtos.request.product.UpdateProductRequest;
import com.turkcell.crm.catalogService.business.dtos.response.product.CreatedProductResponse;
import com.turkcell.crm.catalogService.business.dtos.response.product.GetAllProductResponse;
import com.turkcell.crm.catalogService.business.dtos.response.product.GetByIdProductResponse;
import com.turkcell.crm.catalogService.business.dtos.response.product.UpdatedProductResponse;
import com.turkcell.crm.catalogService.business.rules.ProductBusinessRules;
import com.turkcell.crm.catalogService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.catalogService.dataAccess.ProductRepository;
import com.turkcell.crm.catalogService.entity.Catalog;
import com.turkcell.crm.catalogService.entity.Product;
import com.turkcell.crm.catalogService.kafka.producers.ProductProducer;
import com.turkcell.turkcellcrm.common.events.product.ProductCreatedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductDeletedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductUpdatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    ProductProducer productProducer;


    @Mock
    ProductBusinessRules productBusinessRules;

    @InjectMocks
    ProductManager productManager;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(createProductRequest, Product.class)).thenReturn(product);

        when(productRepository.save(product)).thenReturn(savedProduct);

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        productCreatedEvent.setProductId(1);
        productCreatedEvent.setName("Iphone");

        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapper.map(savedProduct, ProductCreatedEvent.class)).thenReturn(productCreatedEvent);


        CreatedProductResponse createdProductResponse = new CreatedProductResponse();
        createdProductResponse.setId(1);
        createdProductResponse.setName("Iphone");

        when(modelMapper.map(savedProduct, CreatedProductResponse.class)).thenReturn(createdProductResponse);

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
    public void testUpdate() {
        // Test verilerini ayarlayın
        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setId(1);
        Product product = new Product();
        product.setId(1);
        Product savedProduct = new Product();
        savedProduct.setId(1);
        savedProduct.setUpdatedDate(LocalDateTime.now());
        ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent();
        UpdatedProductResponse updatedProductResponse = new UpdatedProductResponse();

        // Mock davranışlarını ayarlayın
        when(productBusinessRules.isProductAlreadyDeleted(updateProductRequest.getId())).thenReturn(product);
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(updateProductRequest, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(modelMapper.map(savedProduct, ProductUpdatedEvent.class)).thenReturn(productUpdatedEvent);
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapper.map(savedProduct, UpdatedProductResponse.class)).thenReturn(updatedProductResponse);


        // Metodu çağırın
        UpdatedProductResponse result = productManager.update(updateProductRequest);

        // Etkileşimleri doğrulayın
        verify(productBusinessRules).isProductAlreadyDeleted(product.getId());
        verify(productRepository).save(product);
        verify(productProducer).sendUpdatedMessage(productUpdatedEvent);

        // Sonuçları doğrulayın
        assertEquals(updatedProductResponse, result);
    }


    @Test
    void getById() {

        // Mock Product instance
        Product product = new Product();
        product.setId(1);
        product.setName("Test Product");

        // Mock optional containing the product
        Optional<Product> optionalProduct = Optional.of(product);

        // Mocking behavior of productRepository.findById()
        when(productRepository.findById(1)).thenReturn(optionalProduct);

        // Mocking behavior of productBusinessRules methods
        when(productBusinessRules.isProductAlreadyDeleted(product.getId())).thenReturn(product);
        when(productBusinessRules.isProductExistById(product.getId())).thenReturn(product);

        // Mocking behavior of modelMapperService
        GetByIdProductResponse response = new GetByIdProductResponse();
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapperService.forResponse().map(product, GetByIdProductResponse.class)).thenReturn(response);

        // Call the method under test
        GetByIdProductResponse actualResponse = productManager.getById(1);

        // Verify that productRepository.findById() is called with the correct id
        verify(productRepository).findById(1);

        // Verify that productBusinessRules methods are called with the correct id
        verify(productBusinessRules).isProductAlreadyDeleted(1);
        verify(productBusinessRules).isProductExistById(1);

        // Verify that modelMapperService.forResponse().map() is called with the correct parameters
        verify(modelMapperService.forResponse()).map(product, GetByIdProductResponse.class);

        // Assert the response
        assertEquals(response, actualResponse);
    }

    @Test
    public void testGetAll() {


        Product product = new Product();
        product.setId(1);
        product.setName("Ürün 1");
        product.setCatalog(new Catalog());
        product.setDescription("Ürün açıklaması");
        product.setPrice(12);
        product.setUnitInStock(10);

        Product product1 = new Product();
        product1.setId(2);
        product1.setName("Ürün 2");
        product1.setCatalog(new Catalog());
        product1.setDescription("Ürün açıklaması 2");
        product1.setPrice(123);
        product1.setUnitInStock(101);

        List<Product> products = Arrays.asList(product, product1);

        // Mock davranışı ayarla
        when(productRepository.findByDeletedDateIsNull()).thenReturn(products);

        GetAllProductResponse response1 = new GetAllProductResponse();
        response1.setId(1);
        response1.setName("Ürün 1");

        GetAllProductResponse response2 = new GetAllProductResponse();
        response2.setId(2);
        response2.setName("Ürün 2");

        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapperService.forResponse().map(product, GetAllProductResponse.class)).thenReturn(response1);
        when(modelMapperService.forResponse().map(product1, GetAllProductResponse.class)).thenReturn(response2);

        // Testi gerçekleştir
        List<GetAllProductResponse> result = productManager.getAll();

        // Sonucu doğrula
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Ürün 1", result.get(0).getName());
        assertEquals(2, result.get(1).getId());
        assertEquals("Ürün 2", result.get(1).getName());

        // Doğru metotların çağrıldığını doğrula
        verify(modelMapperService.forResponse(), times(2)).map(any(), eq(GetAllProductResponse.class));
    }


    @Test
    public void testDelete() {
        // Veri hazırlığı

        Product product = new Product();
        product.setId(1);
        product.setDeletedDate(LocalDateTime.now());

        ProductDeletedEvent productDeletedEvent = new ProductDeletedEvent();



        // Mock davranışı ayarla
        when(productBusinessRules.isProductAlreadyDeleted(product.getId())).thenReturn(product);
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapperService.forRequest().map(product, ProductDeletedEvent.class)).thenReturn(productDeletedEvent);


        // Testi gerçekleştir
        productManager.delete(product.getId());

        // Doğru metotların çağrıldığını ve doğru parametrelerle çağrıldığını doğrula
        verify(productRepository).save(product);
        verify(modelMapperService.forRequest()).map(product, ProductDeletedEvent.class);
        verify(productProducer).sendDeletedMessage(any(ProductDeletedEvent.class));
        verify(productBusinessRules).isProductAlreadyDeleted(product.getId());

        // Silinme tarihini doğrula

        assertEquals(LocalDateTime.now().getDayOfYear(), product.getDeletedDate().getDayOfYear());
    }
}