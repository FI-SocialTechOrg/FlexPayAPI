package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.PayMovement;
import org.springframework.data.repository.CrudRepository;

public interface IPayMovementRepository extends CrudRepository<PayMovement, Integer>{
    PayMovement getPayMovementById (int id);
}
