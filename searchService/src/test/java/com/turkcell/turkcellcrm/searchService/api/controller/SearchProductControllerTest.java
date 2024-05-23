package com.turkcell.turkcellcrm.searchService.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchProductService;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicFilter;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicQuery;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicSort;
import com.turkcell.turkcellcrm.searchService.business.dto.enums.FilterOperator;
import com.turkcell.turkcellcrm.searchService.business.dto.enums.SortDirection;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllProductResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class SearchProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchProductService searchProductService;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        // Mock bir DynamicQuery oluştur
        DynamicFilter filter1 = new DynamicFilter("Huawei", FilterOperator.EQUALS, "John", "string");
        DynamicSort sort1 = new DynamicSort("name", SortDirection.ASC);

        // DynamicQuery oluştur ve içine filtreler ve sıralamalar ekle
        DynamicQuery dynamicQuery = new DynamicQuery(List.of(filter1), List.of(sort1));

        when(objectMapper.writeValueAsString(dynamicQuery));

        // Mock bir GetAllProductResponse oluştur
        GetAllProductResponse response = new GetAllProductResponse();
        response.setProductId(1);
        response.setName("Product1");


        // Mock service metodunun dönüş değerini ayarla
        List<GetAllProductResponse> responseList = Arrays.asList(response);
        when(searchProductService.getAll(dynamicQuery)).thenReturn(responseList);

        // HTTP POST isteği oluştur ve beklentileri kontrol et
        mockMvc.perform(post("/searchservice/api/v1/filters/searchproduct/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dynamicQuery)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product1"));
    }
}