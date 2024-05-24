package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.PayInterest;
import org.springframework.data.repository.CrudRepository;

public interface IPayInterestRepository extends CrudRepository<PayInterest, Integer>{
    PayInterest getPayInterestById (int id);
}
