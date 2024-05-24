package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Store;
import org.springframework.data.repository.CrudRepository;

public interface IStoreRepository extends CrudRepository<Store, Integer> {
    Store getStoreById (int id);
}
