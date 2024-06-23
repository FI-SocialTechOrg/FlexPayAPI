package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
    ShoppingCart getShoppingCartById(int id);
    Optional<ShoppingCart> getShoppingCartByCustomer_Account_Id(int id);
}
