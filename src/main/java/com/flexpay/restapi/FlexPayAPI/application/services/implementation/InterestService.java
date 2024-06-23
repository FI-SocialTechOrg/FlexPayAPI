package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.InterestRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.InterestResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IInterestService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Interest;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.*;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterestService implements IInterestService {
    private final IInterestRepository interestRepository;
    private final ICreditConfigurationRepository creditConfigurationRepository;
    private final ITypeInterestRepository typeInterestRepository;
    private final IPayInterestRepository payInterestRepository;
    private final ICapitalizationPeriodRepository capitalizationPeriodRepository;
    private final ModelMapper modelMapper;

    public InterestService(IInterestRepository interestRepository, ICreditConfigurationRepository creditConfigurationRepository,
                           ITypeInterestRepository typeInterestRepository, IPayInterestRepository payInterestRepository,
                           ICapitalizationPeriodRepository capitalizationPeriodRepository,ModelMapper modelMapper) {
        this.interestRepository = interestRepository;
        this.creditConfigurationRepository = creditConfigurationRepository;
        this.typeInterestRepository = typeInterestRepository;
        this.payInterestRepository = payInterestRepository;
        this.capitalizationPeriodRepository = capitalizationPeriodRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<InterestResponseDTO> getInterestById(int id) {
        Optional<Interest> interestOptional = interestRepository.findById(id);
        if (interestOptional.isPresent()){
            Interest interest = interestOptional.get();
            InterestResponseDTO responseDTO = modelMapper.map(interest, InterestResponseDTO.class);

            return new ApiResponse<>("Interest fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Interest not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<InterestResponseDTO> createInterest(InterestRequestDTO interestRequestDTO) {
        var interest = modelMapper.map(interestRequestDTO, Interest.class);
        interest.setCreditConfiguration(creditConfigurationRepository.getCreditConfigurationById(interestRequestDTO.getCreditConfiguration()));
        interest.setTypeInterest(typeInterestRepository.getTypeInterestById(interestRequestDTO.getTypeInterest()));
        interest.setPayInterest(payInterestRepository.getPayInterestById(interestRequestDTO.getPayInterest()));
        interest.setCapitalizationPeriod(capitalizationPeriodRepository.getCapitalizationPeriodById(interestRequestDTO.getCapitalizationPeriod()));
        interestRepository.save(interest);
        var response = modelMapper.map(interest, InterestResponseDTO.class);

        return new ApiResponse<>("Interest created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<InterestResponseDTO> updateInterest(int id, InterestRequestDTO interestRequestDTO) {
        Optional<Interest> interestOptional = interestRepository.findById(id);
        if (interestOptional.isPresent()){
            Interest interest = interestOptional.get();
            modelMapper.map(interestRequestDTO, interest);
            interest.setCreditConfiguration(creditConfigurationRepository.getCreditConfigurationById(interestRequestDTO.getCreditConfiguration()));
            interest.setTypeInterest(typeInterestRepository.getTypeInterestById(interestRequestDTO.getTypeInterest()));
            interest.setPayInterest(payInterestRepository.getPayInterestById(interestRequestDTO.getPayInterest()));
            interest.setCapitalizationPeriod(capitalizationPeriodRepository.getCapitalizationPeriodById(interestRequestDTO.getCapitalizationPeriod()));
            interestRepository.save(interest);
            InterestResponseDTO responseDTO = modelMapper.map(interest, InterestResponseDTO.class);

            return new ApiResponse<>("Interest updated successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Interest not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<Void> deleteInterest(int id) {
        Optional<Interest> interestOptional = interestRepository.findById(id);

        if (interestOptional.isEmpty()) {
            return new ApiResponse<>("PayCard not found", Estatus.ERROR, null);
        }else {
            interestRepository.deleteById(id);
            return new ApiResponse<>("PayCard deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
