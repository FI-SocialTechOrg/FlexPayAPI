package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

public interface IPaymentMethodRepository extends CrudRepository<PaymentMethod, Integer>{
    PaymentMethod getPaymentMethodById(int id);
}
