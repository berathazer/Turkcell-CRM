package com.turkcell.turkcellcrm.searchService.business.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBasketItemRequest {
        private String name;
        private int productId;
        private double price;
        private int customerId;
        private int accountId;
}
