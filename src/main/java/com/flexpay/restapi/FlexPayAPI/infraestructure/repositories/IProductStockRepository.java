package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.ProductStock;
import org.springframework.data.repository.CrudRepository;
public interface IProductStockRepository extends CrudRepository<ProductStock, Integer> {
    ProductStock getProductStockById (int id);
    boolean existsByStore_IdAndProduct_Id(int storeId, int productId);
}
