package com.turkcell.crm.basketService.business.concretes;

import com.turkcell.crm.basketService.business.abstracts.BasketService;
import com.turkcell.crm.basketService.business.dtos.BasketItemDto;
import com.turkcell.crm.basketService.business.dtos.CreateBasketItemRequest;
import com.turkcell.crm.basketService.business.dtos.CreateOrderRequestByAccountId;
import com.turkcell.crm.basketService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.basketService.dataAccess.RedisRepository;
import com.turkcell.crm.basketService.entity.Basket;
import com.turkcell.crm.basketService.entity.BasketItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BasketManager implements BasketService {

    private RedisRepository redisRepository;

    @Override
    public void add(CreateBasketItemRequest createBasketItemRequests) {

        Basket basket = redisRepository.getBasketByAccountId(createBasketItemRequests.getAccountId());

        if (basket == null) {
            basket = new Basket();
            basket.setAccountId(createBasketItemRequests.getAccountId());
        }

        BasketItem basketItem = new BasketItem();
        basketItem.setProductId(createBasketItemRequests.getProductId());
        basketItem.setName(createBasketItemRequests.getName());
        basketItem.setPrice(createBasketItemRequests.getPrice());
        basket.setCustomerId(createBasketItemRequests.getCustomerId());

        basket.setTotalPrice(basket.getTotalPrice() + basketItem.getPrice());

        basket.getBasketItems().add(basketItem);

        redisRepository.addItem(basket);
    }

    @Override
    public Map<String,Basket> getAllItems() {
        return this.redisRepository.getAllItems();
    }

    @Override
    public void delete (String id){

        this.redisRepository.deleteItem(id);

    }

    @Override
    public void deleteItem(int productId, int accountId) {
        Basket basket = redisRepository.getBasketByAccountId(accountId);
        basket.getBasketItems().remove(productId -1);

        redisRepository.addItem(basket);
    }

    @Override
    public CreateOrderRequestByAccountId getBasketByAccountId(int accountId) {

        Basket basket = redisRepository.getBasketByAccountId(accountId);

        CreateOrderRequestByAccountId createOrderRequestByAccountId = new CreateOrderRequestByAccountId();
        createOrderRequestByAccountId.setAccountId(basket.getAccountId());
        createOrderRequestByAccountId.setTotalPrice(basket.getTotalPrice());
        createOrderRequestByAccountId.setCustomerId(basket.getCustomerId());

        List<BasketItemDto>  basketItemDtos = new ArrayList<>();

        for (BasketItem basketItem : basket.getBasketItems()){

            BasketItemDto basketItemDto = new BasketItemDto();
            basketItemDto.setName(basketItem.getName());
            basketItemDto.setPrice(basketItem.getPrice());
            basketItemDto.setProductId(basketItem.getProductId());

            basketItemDtos.add(basketItemDto);

        }

        createOrderRequestByAccountId.setBasketItemDtos(basketItemDtos);

        return createOrderRequestByAccountId;
    }
}
