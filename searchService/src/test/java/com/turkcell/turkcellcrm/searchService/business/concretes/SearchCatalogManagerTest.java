package com.turkcell.turkcellcrm.searchService.business.concretes;

import com.turkcell.turkcellcrm.common.events.catalog.CatalogCreatedEvent;
import com.turkcell.turkcellcrm.common.events.catalog.CatalogUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.searchService.business.rules.CatalogFilterBusinessRules;
import com.turkcell.turkcellcrm.searchService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchCatalogRepository;
import com.turkcell.turkcellcrm.searchService.entities.Catalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchCatalogManagerTest {
    @Mock
    CatalogFilterBusinessRules catalogFilterBusinessRules;
    @Mock
    ModelMapperService modelMapperService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    SearchCatalogRepository searchCatalogRepository;
    @Mock
    SearchService searchService;
    @InjectMocks
    SearchCatalogManager searchCatalogManager;

    @Test
    void testAdd() {
        CatalogCreatedEvent catalogCreatedEvent=new CatalogCreatedEvent();
        catalogCreatedEvent.setId(222);
        catalogCreatedEvent.setCatalogName("electronic");

        Catalog catalog =new Catalog();
        catalog.setCatalogId(222);
        catalog.setName("electronic");
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(catalogCreatedEvent, Catalog.class)).thenReturn(catalog);

        searchCatalogManager.add(catalogCreatedEvent);
        verify(modelMapperService.forRequest()).map(catalogCreatedEvent,Catalog.class);
        verify(searchCatalogRepository).save(catalog);

        assertDoesNotThrow(()->searchCatalogManager.add(catalogCreatedEvent));

    }

    @Test
    void testGetAllCatalog() {
    }

    @Test
    void testUpdate() {
        CatalogUpdatedEvent catalogUpdatedEvent=new CatalogUpdatedEvent();
        catalogUpdatedEvent.setId(222);
        catalogUpdatedEvent.setCatalogName("electronic");

        Catalog catalog =new Catalog();
        catalog.setCatalogId(222);
        catalog.setName("electronic");

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(catalogUpdatedEvent,Catalog.class)).thenReturn(catalog);

        searchCatalogManager.update(catalogUpdatedEvent);
        verify(modelMapperService.forRequest()).map(catalogUpdatedEvent,Catalog.class);
        verify(searchCatalogRepository).save(catalog);
        assertDoesNotThrow(()->searchCatalogManager.update(catalogUpdatedEvent));
    }
    @Test
    void testDelete() {
        int catalogId = 111;
        Catalog catalog = new Catalog();
        when(catalogFilterBusinessRules.IsCatalogAlreadyDeleted(catalogId)).thenReturn(catalog);

        searchCatalogManager.delete(catalogId);

        verify(catalogFilterBusinessRules).IsCatalogAlreadyDeleted(catalogId);
        verify(searchCatalogRepository).save(catalog);

        assertNotNull(catalog.getDeletedDate());
    }
}