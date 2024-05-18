package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.CreditCard;
import org.springframework.data.repository.CrudRepository;

public interface ICreditCardRepository extends CrudRepository<CreditCard, Integer>{
    CreditCard getCreditCardById (int id);
}
