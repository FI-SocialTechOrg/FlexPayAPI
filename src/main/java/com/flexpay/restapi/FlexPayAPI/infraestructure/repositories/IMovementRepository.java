package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Movement;
import org.springframework.data.repository.CrudRepository;

public interface IMovementRepository extends CrudRepository<Movement, Integer> {
    Movement getMovementById(int id);
}
