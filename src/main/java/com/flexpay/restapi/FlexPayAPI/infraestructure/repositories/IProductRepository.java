package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Integer>{

    Product getProductById (int id);
}
