package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.CreditConfiguration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICreditConfigurationRepository extends CrudRepository<CreditConfiguration, Integer>{
    CreditConfiguration getCreditConfigurationById (int id);
    Optional<CreditConfiguration> getCreditConfigurationByStore_Account_Id(int id);
    Optional<CreditConfiguration> getCreditConfigurationByStore_Id(int id);
}
