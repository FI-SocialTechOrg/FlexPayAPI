package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CreditCardRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CreditCardResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.ICreditCardService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.CreditCard;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.ICreditCardRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IPayCardRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IShoppingCartRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IStateCardRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CreditCardService implements ICreditCardService {
    private final ICreditCardRepository creditCardRepository;
    private final IPayCardRepository payCardRepository;
    private final IStateCardRepository stateCardRepository;
    private final IShoppingCartRepository shoppingCartRepository;
    private final ModelMapper modelMapper;

    public CreditCardService(ICreditCardRepository creditCardRepository, IPayCardRepository payCardRepository, IStateCardRepository stateCardRepository, IShoppingCartRepository shoppingCartRepository, ModelMapper modelMapper) {
        this.creditCardRepository = creditCardRepository;
        this.payCardRepository = payCardRepository;
        this.stateCardRepository = stateCardRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.modelMapper = modelMapper;
    }

    public ApiResponse<CreditCardResponseDTO> getCreditCardById(int id) {
        Optional<CreditCard> creditCardOptional = creditCardRepository.findById(id);
        if (creditCardOptional.isPresent()){
            CreditCard creditCard = creditCardOptional.get();
            CreditCardResponseDTO responseDTO = modelMapper.map(creditCard, CreditCardResponseDTO.class);

            return new ApiResponse<>("Customer fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Customer not found", Estatus.ERROR, null);
        }
    }

    public ApiResponse<CreditCardResponseDTO> createCreditCard(CreditCardRequestDTO creditCardRequestDTO) {
        var creditCard = modelMapper.map(creditCardRequestDTO, CreditCard.class);
        creditCard.setPayCard(payCardRepository.getPayCardById(creditCardRequestDTO.getPayCard()));
        creditCard.setStateCard(stateCardRepository.getStateCardById(creditCardRequestDTO.getStateCard()));
        creditCard.setShoppingCart(shoppingCartRepository.getShoppingCartById(creditCardRequestDTO.getShoppingCart()));
        creditCardRepository.save(creditCard);
        var response = modelMapper.map(creditCard, CreditCardResponseDTO.class);

        return new ApiResponse<>("Customer created successfully", Estatus.SUCCESS, response);
    }

    public ApiResponse<CreditCardResponseDTO> updateCreditCard(int id, CreditCardRequestDTO creditCardRequestDTO) {
        Optional<CreditCard> creditCardOptional = creditCardRepository.findById(id);
        if (creditCardOptional.isEmpty()) {
            return new ApiResponse<>("Customer not found", Estatus.ERROR, null);
        } else {
            CreditCard creditCard = creditCardOptional.get();
            modelMapper.map(creditCardRequestDTO, creditCard);
            creditCard.setPayCard(payCardRepository.getPayCardById(creditCardRequestDTO.getPayCard()));
            creditCard.setStateCard(stateCardRepository.getStateCardById(creditCardRequestDTO.getStateCard()));
            creditCard.setShoppingCart(shoppingCartRepository.getShoppingCartById(creditCardRequestDTO.getShoppingCart()));
            creditCardRepository.save(creditCard);
            CreditCardResponseDTO response = modelMapper.map(creditCard, CreditCardResponseDTO.class);
            return new ApiResponse<>("Customer updated successfully", Estatus.SUCCESS, response);
        }
    }

    public ApiResponse<Void> deleteCreditCard(int id) {
        Optional<CreditCard> creditCardOptional = creditCardRepository.findById(id);
        if (creditCardOptional.isEmpty()) {
            return new ApiResponse<>("Customer not found", Estatus.ERROR, null);
        } else {
            creditCardRepository.deleteById(id);
            return new ApiResponse<>("Customer deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
