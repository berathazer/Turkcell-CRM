package com.turkcell.crm.basketService.business.concreates;

import com.turkcell.crm.basketService.business.abstracts.BasketService;
import com.turkcell.crm.basketService.dataAccess.RedisRepository;
import com.turkcell.crm.basketService.entity.Basket;
import com.turkcell.crm.basketService.entity.BasketItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class BasketManager implements BasketService {

    private RedisRepository redisRepository;

    @Override
    public void add(String customerId, String productId) {

        Basket basket = redisRepository.getBasketByCustomerId(customerId);

        if (basket == null) {
            basket = new Basket();
            basket.setCustomerId(customerId);
        }
        BasketItem basketItem = new BasketItem("1","Modem",2000);
        basket.setCustomerId(customerId);
        basket.setTotalPrice(basket.getTotalPrice()+basketItem.getPrice());
        basket.getBasketItems().add(basketItem);
        redisRepository.addItem(basket);
    }

    @Override
    public Map<String, Basket> getAllItems() {
        return redisRepository.getAllItems();
    }
}
