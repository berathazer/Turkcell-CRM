package com.turkcell.turkcellcrm.searchService.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchProductService;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicFilter;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicQuery;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicSort;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class SearchProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SearchProductService searchProductService;

    @InjectMocks
    private SearchProductController searchProductController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(searchProductController).build();
    }

    @Test
    public void testGetAll() throws Exception {
        List<DynamicFilter> filters = Collections.emptyList();
        List<DynamicSort> sorts = Collections.emptyList();
        DynamicQuery dynamicQuery = new DynamicQuery(filters, sorts);

        GetAllProductResponse productResponse1 = new GetAllProductResponse();
        GetAllProductResponse productResponse2 = new GetAllProductResponse();
        List<GetAllProductResponse> productList = Arrays.asList(productResponse1, productResponse2);

        // Mock davranışlarını ayarlayın
        when(searchProductService.getAll(dynamicQuery)).thenReturn(productList);

        // Metodu çağırın ve doğrulamaları yapın
        mockMvc.perform(post("/searchservice/api/v1/filters/searchproduct/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dynamicQuery)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productList)))
                .andDo(MockMvcResultHandlers.print());

        // Servis metodunun çağrıldığını doğrulayın
        verify(searchProductService, times(1)).getAll(dynamicQuery);
    }
}