package com.turkcell.turkcellcrm.common.events.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {
    private int productId;
    private int catalogId;
    private String name;
    private double price;
}
