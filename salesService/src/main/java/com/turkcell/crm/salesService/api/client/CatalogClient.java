package com.turkcell.crm.salesService.api.client;

import com.turkcell.crm.salesService.business.dto.ProductPropertyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "catalogService", url = "http://localhost:10042/customerservice/api/v1/customers/individualcustomers")
//public interface CatalogClient {
//    @GetMapping("/getById/{productId}")
//    List<ProductPropertyResponseDto> getProductPropertyIdByProductId(@PathVariable int productId);
//}
