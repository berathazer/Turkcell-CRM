package com.turkcell.turkcellcrm.common.events.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdatedEvent {
    private int productId;
    private String name;
    private int catalogId;
    private double price;
}
