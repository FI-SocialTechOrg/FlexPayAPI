package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.ProductStock;
import org.springframework.data.repository.CrudRepository;
public interface IProductStockRepository extends CrudRepository<ProductStock, Integer> {
    ProductStock getProductStockById (int id);
}
