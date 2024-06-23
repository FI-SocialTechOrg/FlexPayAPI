package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.CapitalizationPeriod;
import org.springframework.data.repository.CrudRepository;

public interface ICapitalizationPeriodRepository extends CrudRepository<CapitalizationPeriod, Integer> {
    CapitalizationPeriod getCapitalizationPeriodById(int id);
}
