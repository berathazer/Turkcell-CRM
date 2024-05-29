package com.turkcell.crm.basketService.business.concretes;

import com.turkcell.crm.basketService.business.abstracts.BasketService;
import com.turkcell.crm.basketService.business.dtos.CreateBasketItemRequest;
import com.turkcell.crm.basketService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.basketService.dataAccess.RedisRepository;
import com.turkcell.crm.basketService.entity.Basket;
import com.turkcell.crm.basketService.entity.BasketItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BasketManager implements BasketService {

    private RedisRepository redisRepository;

    @Override
    public void add(CreateBasketItemRequest createBasketItemRequest) {

        Basket basket = this.redisRepository.getBasketByAccountId(createBasketItemRequest.getCustomerId());

        if (basket == null) {
            basket = new Basket();
            basket.setAccountId(createBasketItemRequest.getCustomerId());
        }

        BasketItem basketItem = new BasketItem();
        basketItem.setName(createBasketItemRequest.getName());
        basketItem.setPrice(createBasketItemRequest.getPrice());
        basketItem.setProductId(createBasketItemRequest.getProductId());

        double totalPrice = calculateTotalPrice(basket.getBasketItems());
        basket.setTotalPrice(totalPrice);

        basket.getBasketItems().add(basketItem);
        this.redisRepository.addItem(basket);
    }

    @Override
    public Map<String,Basket> getAllItems() {
        return this.redisRepository.getAllItems();
    }

    public double calculateTotalPrice(List<BasketItem> basketItems){

        double totalPrice = 0;

        for (BasketItem basketItem: basketItems) {
            totalPrice += basketItem.getPrice();
        }
        return totalPrice;
    }
}
