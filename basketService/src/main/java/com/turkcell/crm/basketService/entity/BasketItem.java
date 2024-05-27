package com.turkcell.crm.basketService.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.turkcell.crm.basketService.core.entites.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BasketItem extends BaseEntity implements Serializable {


    private String productId;
    private String productName;
    private double price;

}
