package com.turkcell.crm.basketService.entity;

import com.turkcell.crm.basketService.core.entites.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketItem extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 354801933777929859L;
    private String productId;
    private String name;
    private double price;
}
