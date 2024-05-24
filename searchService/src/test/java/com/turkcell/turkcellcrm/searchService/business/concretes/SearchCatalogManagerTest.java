package com.turkcell.turkcellcrm.searchService.business.concretes;

import com.turkcell.turkcellcrm.common.events.catalog.CatalogCreatedEvent;
import com.turkcell.turkcellcrm.common.events.catalog.CatalogUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCatalogResponse;
import com.turkcell.turkcellcrm.searchService.business.rules.CatalogFilterBusinessRules;
import com.turkcell.turkcellcrm.searchService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchCatalogRepository;
import com.turkcell.turkcellcrm.searchService.entities.Catalog;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchCatalogManagerTest {

    @Mock
    private SearchCatalogRepository searchCatalogRepository;

    @Mock
    private CatalogFilterBusinessRules catalogFilterBusinessRules;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ModelMapperService modelMapperService;

    @InjectMocks
    private SearchCatalogManager searchCatalogManager;

    @Test
    void testAdd() {
        CatalogCreatedEvent catalogCreatedEvent = new CatalogCreatedEvent();
        Catalog catalog = new Catalog();

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(catalogCreatedEvent,Catalog.class)).thenReturn(catalog);

        searchCatalogManager.add(catalogCreatedEvent);

        verify(modelMapperService.forRequest()).map(catalogCreatedEvent,Catalog.class);
        verify(searchCatalogRepository).save(catalog);
        assertDoesNotThrow(()->searchCatalogManager.add(catalogCreatedEvent));

    }

    @Test
    void testGetAllCatalog() {

        Catalog catalog1 = new Catalog();
        catalog1.setCatalogId(1);
        catalog1.setName("Catalog 1");
        catalog1.setId(new ObjectId());

        Catalog catalog2 = new Catalog();
        catalog2.setCatalogId(2);
        catalog2.setName("Catalog 2");
        catalog2.setId(new ObjectId());

       List<Catalog> catalogList = Arrays.asList(catalog1, catalog2);

        // Mock repository'nin findAll() metodunu hazırla
        when(searchCatalogRepository.findAll()).thenReturn(catalogList);

        // Mock model mapper service'nin map() metodunu hazırla
        GetAllCatalogResponse response1 = new GetAllCatalogResponse();
        response1.setCatalogId(catalog1.getCatalogId());
        response1.setName(catalog1.getName());

        GetAllCatalogResponse response2 = new GetAllCatalogResponse();
        response2.setCatalogId(catalog2.getCatalogId());
        response2.setName(catalog2.getName());

        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapperService.forResponse().map(catalog1, GetAllCatalogResponse.class)).thenReturn(response1);
        when(modelMapperService.forResponse().map(catalog2, GetAllCatalogResponse.class)).thenReturn(response2);

        // Test
        List<GetAllCatalogResponse> result = searchCatalogManager.getAllCatalog();

        // Assert
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getCatalogId());
        assertEquals("Catalog 1", result.get(0).getName());
        assertEquals(2, result.get(1).getCatalogId());
        assertEquals("Catalog 2", result.get(1).getName());

        // Verify
        verify(searchCatalogRepository).findAll();
        verify(modelMapperService.forResponse()).map(catalog1, GetAllCatalogResponse.class);
        verify(modelMapperService.forResponse()).map(catalog2, GetAllCatalogResponse.class);
    }

    @Test
    void testUpdate() {
        CatalogUpdatedEvent catalogUpdatedEvent = new CatalogUpdatedEvent();
        Catalog catalog = new Catalog();

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapperService.forRequest().map(catalogUpdatedEvent, Catalog.class)).thenReturn(catalog);

        searchCatalogManager.update(catalogUpdatedEvent);

        verify(modelMapperService.forRequest()).map(catalogUpdatedEvent, Catalog.class);
        verify(searchCatalogRepository).save(catalog);
        assertDoesNotThrow(()->searchCatalogManager.update(catalogUpdatedEvent));
    }

    @Test
    void testDelete_Successful() {
        int catalogId = 150;
        Catalog catalog = new Catalog();

        // Mock the business rule to return the product
        when(catalogFilterBusinessRules.IsCatalogAlreadyDeleted(catalogId)).thenReturn(catalog);

        // Perform the delete operation
        searchCatalogManager.delete(catalogId);

        // Verify that the methods were called
        verify(catalogFilterBusinessRules).IsCatalogAlreadyDeleted(catalogId);
        verify(searchCatalogRepository).save(catalog);

        // Check if the deleted date is set
        assertNotNull(catalog.getDeletedDate());
    }
}