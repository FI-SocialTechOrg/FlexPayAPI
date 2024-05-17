package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface IShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
    ShoppingCart getShoppingCartById(int id);
}
