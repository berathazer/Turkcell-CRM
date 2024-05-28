package com.turkcell.crm.basketService.dataAccess;

import com.turkcell.crm.basketService.entity.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer> {
    Basket findBasketByAccountId(int accountId);
}
