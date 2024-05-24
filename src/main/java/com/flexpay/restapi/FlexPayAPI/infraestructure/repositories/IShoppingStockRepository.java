package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.ShoppingStock;
import org.springframework.data.repository.CrudRepository;

public interface IShoppingStockRepository extends CrudRepository<ShoppingStock, Integer> {
    ShoppingStock getShoppingStockById (int id);
}