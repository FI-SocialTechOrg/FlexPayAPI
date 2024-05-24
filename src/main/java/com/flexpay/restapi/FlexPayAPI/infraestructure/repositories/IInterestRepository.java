package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Interest;
import org.springframework.data.repository.CrudRepository;

public interface IInterestRepository extends CrudRepository<Interest, Integer>{
    Interest getInterestById (int id);
}
