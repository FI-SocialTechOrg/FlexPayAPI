package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.PayCard;
import org.springframework.data.repository.CrudRepository;

public interface IPayCardRepository extends CrudRepository<PayCard, Integer>{
    PayCard getPayCardById (int id);
}
