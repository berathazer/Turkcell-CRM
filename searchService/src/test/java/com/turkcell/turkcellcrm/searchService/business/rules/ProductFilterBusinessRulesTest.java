package com.turkcell.turkcellcrm.searchService.business.rules;

import com.turkcell.turkcellcrm.searchService.business.messages.ProductFilterBusinessRulesMessages;
import com.turkcell.turkcellcrm.searchService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchProductRepository;
import com.turkcell.turkcellcrm.searchService.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductFilterBusinessRulesTest {

    @Mock
    private SearchProductRepository searchProductRepository;

    @InjectMocks
    private ProductFilterBusinessRules productFilterBusinessRules;

    @Test
    void shouldReturnProductWhenProductIsNotDeleted() {

        Product product = new Product();
        product.setProductId(1);
        product.setDeletedDate(null);

        when(searchProductRepository.findProductByProductId(1)).thenReturn(Optional.of(product));

        Product result = productFilterBusinessRules.IsProductAlreadyDeleted(1);

        assertNotNull(result);
        assertEquals(1, result.getProductId());
        assertNull(result.getDeletedDate());
        verify(searchProductRepository).findProductByProductId(1);
    }

    @Test
    void shouldThrowBusinessExceptionWhenProductDoesNotExist() {
        when(searchProductRepository.findProductByProductId(1)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productFilterBusinessRules.IsProductAlreadyDeleted(1);
        });

        assertEquals(ProductFilterBusinessRulesMessages.PRODUCT_NOT_EXISTS, exception.getMessage());
        verify(searchProductRepository).findProductByProductId(1);
    }

    @Test
    void shouldThrowBusinessExceptionWhenProductIsAlreadyDeleted() {
        Product product = new Product();
        product.setProductId(1);

        product.setDeletedDate(LocalDateTime.now());
        when(searchProductRepository.findProductByProductId(1)).thenReturn(Optional.of(product));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productFilterBusinessRules.IsProductAlreadyDeleted(1);
        });

        assertEquals(ProductFilterBusinessRulesMessages.PRODUCT_IS_ALREADY_DELETED, exception.getMessage());
        verify(searchProductRepository).findProductByProductId(1);
    }
}