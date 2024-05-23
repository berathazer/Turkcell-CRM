package com.turkcell.turkcellcrm.searchService.dataAccess;

import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchCatalogService;
import com.turkcell.turkcellcrm.searchService.entities.Catalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchCatalogRepositoryTest {

    @Mock
    private SearchCatalogRepository searchCatalogRepository;


    @Test
    void testFindCatalogByCatalogId() {
        // Mock repository davranışını ayarlayın
        int catalogId = 123; // Test için bir katalog kimliği
        Catalog expectedCatalog = new Catalog();
        expectedCatalog.setCatalogId(catalogId);
        when(searchCatalogRepository.findCatalogByCatalogId(catalogId)).thenReturn(Optional.of(expectedCatalog));

        // Repository metodunu çağırın
        Optional<Catalog> actualCatalogOptional = searchCatalogRepository.findCatalogByCatalogId(catalogId);

        // Sonucun beklenen katalog olup olmadığını kontrol edin
        assertTrue(actualCatalogOptional.isPresent()); // Opsiyonel bir katalogın mevcut olup olmadığını kontrol edin
        Catalog actualCatalog = actualCatalogOptional.get();
        assertEquals(expectedCatalog.getCatalogId(), actualCatalog.getCatalogId());
        // Diğer katalog alanlarını da gerektiği gibi kontrol edin
    }
}