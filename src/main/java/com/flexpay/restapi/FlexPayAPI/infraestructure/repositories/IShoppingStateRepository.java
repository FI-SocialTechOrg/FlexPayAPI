package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.ShoppingState;
import org.springframework.data.repository.CrudRepository;

public interface IShoppingStateRepository extends CrudRepository<ShoppingState, Integer> {
    ShoppingState getShoppingStateById(int id);
}
