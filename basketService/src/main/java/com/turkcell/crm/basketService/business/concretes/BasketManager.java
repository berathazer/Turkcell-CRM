package com.turkcell.crm.basketService.business.concretes;

import com.turkcell.crm.basketService.business.abstracts.BasketService;
import com.turkcell.crm.basketService.business.dtos.GetAllProductResponse;
import com.turkcell.crm.basketService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.basketService.dataAccess.BasketRepository;
import com.turkcell.crm.basketService.entity.Basket;
import com.turkcell.crm.basketService.entity.BasketItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BasketManager implements BasketService {

    private BasketRepository basketRepository;
    private ModelMapperService modelMapperService;

    @Override
    public void add(List<GetAllProductResponse> getAllProductResponses,int accountId) {

        Basket basket = this.basketRepository.findBasketByAccountId(accountId);

        if (basket == null) {
            basket = new Basket();
            basket.setAccountId(accountId);
        }

        List<BasketItem> basketItems =
                getAllProductResponses.stream().map(getAllProductResponse -> this.modelMapperService.forRequest().
                        map(getAllProductResponses, BasketItem.class)).toList();

        basket.setBasketItems(basketItems);
    }

    @Override
    public List<Basket> getAllItems() {
        Iterable<Basket> iterable = this.basketRepository.findAll();
        List<Basket> baskets = new ArrayList<>();
        iterable.forEach(baskets::add);
        return baskets;
    }

}
