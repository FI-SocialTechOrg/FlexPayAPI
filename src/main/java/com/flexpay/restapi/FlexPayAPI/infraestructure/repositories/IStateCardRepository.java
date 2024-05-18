package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.StateCard;
import org.springframework.data.repository.CrudRepository;

public interface IStateCardRepository extends CrudRepository<StateCard, Integer>{
    StateCard getStateCardById(int id);
}
