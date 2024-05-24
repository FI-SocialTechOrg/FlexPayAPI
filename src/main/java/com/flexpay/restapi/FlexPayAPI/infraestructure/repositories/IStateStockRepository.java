package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.StateStock;
import org.springframework.data.repository.CrudRepository;

public interface IStateStockRepository extends CrudRepository<StateStock, Integer> {
    StateStock getStateStockById (int id);
}
