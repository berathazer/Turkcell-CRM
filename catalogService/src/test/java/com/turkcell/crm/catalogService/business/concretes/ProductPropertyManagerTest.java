package com.turkcell.crm.catalogService.business.concretes;

import com.turkcell.crm.catalogService.business.abstracts.ProductService;
import com.turkcell.crm.catalogService.business.dtos.request.productProperties.CreateProductPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.request.productProperties.UpdateProductPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.response.productProperties.CreatedProductPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.productProperties.GetAllProductPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.productProperties.GetByIdProductPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.productProperties.UpdatedProductProductResponse;
import com.turkcell.crm.catalogService.business.rules.ProductPropertyBusinessRules;
import com.turkcell.crm.catalogService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.catalogService.dataAccess.ProductPropertyRepository;
import com.turkcell.crm.catalogService.entity.ProductProperty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductPropertyManagerTest {

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private ProductPropertyRepository productPropertyRepository;

    @Mock
    private ProductPropertyBusinessRules productPropertyBusinessRules;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductPropertyManager productPropertyManager;

    @Mock
    private ModelMapper modelMapper;


    @Test
    void testAdd() {
        CreateProductPropertyRequest request = new CreateProductPropertyRequest();
        request.setProductId(1);
        ProductProperty productProperty = new ProductProperty();
        productProperty.setId(0);
        ProductProperty savedProductProperty = new ProductProperty();
        savedProductProperty.setId(1);

        CreatedProductPropertyResponse response1 = new CreatedProductPropertyResponse();
        response1.setId(1);

        when(productService.getById(request.getProductId())).thenReturn(any());

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(request,ProductProperty.class)).thenReturn(productProperty);

        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapper.map(savedProductProperty,CreatedProductPropertyResponse.class)).thenReturn(response1);


        when(productPropertyRepository.save(any(ProductProperty.class))).thenReturn(savedProductProperty);

        CreatedProductPropertyResponse response = productPropertyManager.add(request);

        assertEquals(1, response.getId());

        verify(productService).getById(request.getProductId());
        verify(productPropertyRepository).save(any(ProductProperty.class));
        verify(modelMapperService.forResponse(), times(1)).map(any(), eq(CreatedProductPropertyResponse.class));
        verify(modelMapperService.forRequest(), times(1)).map(any(), eq(CreateProductPropertyRequest.class));
    }

    @Test
    void testGetAll() {
        ProductProperty productProperty = new ProductProperty();
        productProperty.setId(1);
        List<ProductProperty> productProperties = List.of(productProperty);

        CreatedProductPropertyResponse createdProductPropertyResponse = new CreatedProductPropertyResponse();

        when(productPropertyRepository.findByDeletedDateIsNull()).thenReturn(productProperties);

        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapper.map(productProperty,CreatedProductPropertyResponse.class)).thenReturn(createdProductPropertyResponse);

        List<GetAllProductPropertyResponse> responses = productPropertyManager.getAll();

        assertEquals(1, responses.size());
        assertEquals(1, responses.get(0).getId());

        verify(productPropertyRepository).findByDeletedDateIsNull();
    }

    @Test
    void testUpdate() {
        UpdateProductPropertyRequest request = new UpdateProductPropertyRequest();
        request.setId(1);
        request.setProductId(1);
        ProductProperty productProperty = new ProductProperty();
        productProperty.setId(1);

        UpdatedProductProductResponse updatedProductProductResponse=new UpdatedProductProductResponse();
        updatedProductProductResponse.setId(1);

        when(productService.getById(request.getProductId())).thenReturn(any());
        when(productPropertyBusinessRules.isProductPropertyAlreadyDeleted(request.getId()));
        when(productPropertyBusinessRules.isProductPropertyExistById(request.getId()));

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(request,ProductProperty.class)).thenReturn(productProperty);

        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapper.map(productProperty,UpdatedProductProductResponse.class)).thenReturn(updatedProductProductResponse);

        when(productPropertyRepository.save(any(ProductProperty.class))).thenReturn(productProperty);

        UpdatedProductProductResponse response = productPropertyManager.update(request);

        assertEquals(1, response.getId());
        verify(productService).getById(request.getProductId());
        verify(productPropertyBusinessRules).isProductPropertyAlreadyDeleted(request.getId());
        verify(productPropertyBusinessRules).isProductPropertyExistById(request.getId());
        verify(productPropertyRepository).save(any(ProductProperty.class));
    }

    @Test
    void testGetById() {
        int id = 1;
        ProductProperty productProperty = new ProductProperty();
        productProperty.setId(id);
        Optional<ProductProperty> optionalProductProperty = Optional.of(productProperty);

        doNothing().when(productPropertyBusinessRules).isProductPropertyAlreadyDeleted(id);
        doNothing().when(productPropertyBusinessRules).isProductPropertyExistById(id);
        when(productPropertyRepository.findById(id)).thenReturn(optionalProductProperty);
        when(modelMapperService.forResponse()).thenReturn(modelMapper);

        GetByIdProductPropertyResponse response = productPropertyManager.getById(id);
        assertEquals(id, response.getId());
        verify(productPropertyBusinessRules).isProductPropertyAlreadyDeleted(id);
        verify(productPropertyBusinessRules).isProductPropertyExistById(id);
        verify(productPropertyRepository).findById(id);
    }

    @Test
    void testDelete() {
        int id = 1;
        ProductProperty productProperty = new ProductProperty();
        productProperty.setId(id);

        when(productPropertyBusinessRules.isProductPropertyAlreadyDeleted(id)).thenReturn(productProperty);

        productPropertyManager.delete(id);

        assertNotNull(productProperty.getDeletedDate());
        verify(productPropertyBusinessRules).isProductPropertyAlreadyDeleted(id);
        verify(productPropertyRepository).save(productProperty);
    }
}