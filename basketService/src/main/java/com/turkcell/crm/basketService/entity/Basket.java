package com.turkcell.crm.basketService.entity;

import com.turkcell.crm.basketService.core.entites.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Basket  implements Serializable {

    private String id;
    private int accountId;
    private double totalPrice;
    private List<BasketItem> basketItems;

    public Basket(){
        this.id= UUID.randomUUID().toString();
        this.basketItems = new ArrayList<>();
    }
}
