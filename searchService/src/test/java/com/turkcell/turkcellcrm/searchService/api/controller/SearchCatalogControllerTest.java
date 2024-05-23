package com.turkcell.turkcellcrm.searchService.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchCatalogService;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCatalogResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SearchCatalogControllerTest {


    private MockMvc mockMvc;

    @Mock
    private SearchCatalogService searchCatalogService;

    @InjectMocks
    private SearchCatalogController searchCatalogController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(searchCatalogController).build();
    }

    @Test
    public void testGetAll() throws Exception {
        // Test verilerini ayarlayın
        GetAllCatalogResponse catalogResponse1 = new GetAllCatalogResponse();
        GetAllCatalogResponse catalogResponse2 = new GetAllCatalogResponse();
        List<GetAllCatalogResponse> catalogList = Arrays.asList(catalogResponse1, catalogResponse2);

        // Mock davranışlarını ayarlayın
        when(searchCatalogService.getAllCatalog()).thenReturn(catalogList);

        // Metodu çağırın ve doğrulamaları yapın
        mockMvc.perform(get("/searchservice/api/v1/filters/searchcatalog/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(catalogList)))
                .andDo(MockMvcResultHandlers.print());

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchCatalogService, times(1)).getAllCatalog();
    }
}