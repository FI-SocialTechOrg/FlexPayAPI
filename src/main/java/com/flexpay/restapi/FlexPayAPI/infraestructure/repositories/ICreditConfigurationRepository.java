package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.CreditConfiguration;
import org.springframework.data.repository.CrudRepository;

public interface ICreditConfigurationRepository extends CrudRepository<CreditConfiguration, Integer>{
    CreditConfiguration getCreditConfigurationById (int id);
}
