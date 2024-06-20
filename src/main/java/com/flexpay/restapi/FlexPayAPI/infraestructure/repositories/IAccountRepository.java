package com.flexpay.restapi.FlexPayAPI.infraestructure.repositories;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IAccountRepository extends CrudRepository<Account, Integer>{
    Account getAccountById (int id);
    Optional<Account> findByEmailOrUserName(String email, String userName);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
