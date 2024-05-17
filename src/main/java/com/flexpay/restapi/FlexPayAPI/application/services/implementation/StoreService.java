package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.StoreRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.AccountResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CustomerResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StoreResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IStoreService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Account;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Customer;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Store;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IAccountRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IStoreRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService implements IStoreService {
    private final IStoreRepository storeRepository;
    private final IAccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public StoreService(IStoreRepository storeRepository, IAccountRepository accountRepository, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<StoreResponseDTO> getStoreById(int id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if (storeOptional.isPresent()){
            Store store = storeOptional.get();
            StoreResponseDTO responseDTO = convertToCustomerResponseDTO(store);
            return new ApiResponse<>("Store fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Store not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<StoreResponseDTO>> getAllStores() {
        List<Store> storeList = (List<Store>) storeRepository.findAll();
        List<StoreResponseDTO> storeResponseDTOList = storeList.stream()
                .map(this::convertToCustomerResponseDTO)
                .collect(Collectors.toList());

        return new ApiResponse<>("All stores fetched successfully", Estatus.SUCCESS, storeResponseDTOList);
    }

    @Override
    public ApiResponse<StoreResponseDTO> createStore(StoreRequestDTO storeRequestDTO) {
        var store = modelMapper.map(storeRequestDTO, Store.class);
        store.setAccount(accountRepository.getAccountById(storeRequestDTO.getAccount()));
        storeRepository.save(store);
        var response = convertToCustomerResponseDTO(store);

        return new ApiResponse<>("Store created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<StoreResponseDTO> updateStore(int id, StoreRequestDTO storeRequestDTO) {
        Optional<Store> storeOptional = storeRepository.findById(id);

        if (storeOptional.isEmpty()) {
            return new ApiResponse<>("Store not found", Estatus.ERROR, null);
        }else {
            Store store = storeOptional.get();
            modelMapper.map(storeRequestDTO, store);
            store.setAccount(accountRepository.getAccountById(storeRequestDTO.getAccount()));
            storeRepository.save(store);
            StoreResponseDTO response = convertToCustomerResponseDTO(store);
            return new ApiResponse<>("Store updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteStore(int id) {
        Optional<Store> storeOptional = storeRepository.findById(id);

        if (storeOptional.isEmpty()) {
            return new ApiResponse<>("Store not found", Estatus.ERROR, null);
        }else {
            storeRepository.deleteById(id);
            return new ApiResponse<>("Store deleted successfully", Estatus.SUCCESS, null);
        }
    }


    private StoreResponseDTO convertToCustomerResponseDTO(Store store) {
        StoreResponseDTO responseDTO = modelMapper.map(store, StoreResponseDTO.class);

        Account account = store.getAccount();
        AccountResponseDTO accountResponseDTO = AccountResponseDTO.builder()
                .id(account.getId())
                .email(account.getEmail())
                .userName(account.getUserName())
                .role(account.getRole())
                .build();

        responseDTO.setAccount(accountResponseDTO);

        return responseDTO;
    }
}
