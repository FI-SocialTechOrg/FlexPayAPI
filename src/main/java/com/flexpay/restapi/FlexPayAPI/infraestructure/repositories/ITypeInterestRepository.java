package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.TypeInterest;
import org.springframework.data.repository.CrudRepository;

public interface ITypeInterestRepository extends CrudRepository<TypeInterest, Integer>{
    TypeInterest getTypeInterestById (int id);
}
