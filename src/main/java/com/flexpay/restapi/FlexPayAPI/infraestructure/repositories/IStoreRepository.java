package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IStoreRepository extends CrudRepository<Store, Integer> {
    Store getStoreById (int id);
    Optional<Store> getStoreByAccount_Id(int id);
}
