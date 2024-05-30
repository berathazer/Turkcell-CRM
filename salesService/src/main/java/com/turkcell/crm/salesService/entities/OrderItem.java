package com.turkcell.crm.salesService.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem{

    private int productId;
    private String productName;
    //private List<ProductConfig> productConfigs;
}
