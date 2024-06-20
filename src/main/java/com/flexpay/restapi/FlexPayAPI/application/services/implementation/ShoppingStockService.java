package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ShoppingStockRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ShoppingStockResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IShoppingStockService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.ShoppingStock;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IProductStockRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IShoppingCartRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IShoppingStockRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingStockService implements IShoppingStockService {

    private final IShoppingStockRepository shoppingStockRepository;
    private final IProductStockRepository productStockRepository;
    private final IShoppingCartRepository shoppingCartRepository;
    private final ModelMapper modelMapper;

    public ShoppingStockService(IShoppingStockRepository shoppingStockRepository, IProductStockRepository productStockRepository, IShoppingCartRepository shoppingCartRepository, ModelMapper modelMapper) {
        this.shoppingStockRepository = shoppingStockRepository;
        this.productStockRepository = productStockRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<ShoppingStockResponseDTO> getShoppingStockById(int id) {
        Optional<ShoppingStock> shoppingStockOptional = shoppingStockRepository.findById(id);
        if (shoppingStockOptional.isPresent()) {
            ShoppingStock shoppingStock = shoppingStockOptional.get();
            ShoppingStockResponseDTO responseDTO = modelMapper.map(shoppingStock, ShoppingStockResponseDTO.class);
            return new ApiResponse<>("ShoppingStock fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("ShoppingStock not found", Estatus.ERROR, null);
        }
    }



    @Override
    public ApiResponse<List<ShoppingStockResponseDTO>> getAllShoppingStocks() {
        List<ShoppingStock> shoppingStockList = (List<ShoppingStock>) shoppingStockRepository.findAll();
        List<ShoppingStockResponseDTO> shoppingStockResponseDTOList = shoppingStockList.stream()
                .map(entity-> modelMapper.map(entity, ShoppingStockResponseDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("All shoppingStocks fetched successfully", Estatus.SUCCESS, shoppingStockResponseDTOList);
    }

    @Override
    public ApiResponse<ShoppingStockResponseDTO> createShoppingStock(ShoppingStockRequestDTO shoppingStockRequestDTO) {
        var shoppingStock = modelMapper.map(shoppingStockRequestDTO, ShoppingStock.class);
        shoppingStock.setProductStock(productStockRepository.getProductStockById(shoppingStockRequestDTO.getProductStock()));
        shoppingStock.setShoppingCart(shoppingCartRepository.getShoppingCartById(shoppingStockRequestDTO.getShoppingCart()));
        shoppingStockRepository.save(shoppingStock);
        var response = modelMapper.map(shoppingStock, ShoppingStockResponseDTO.class);

        return new ApiResponse<>("ShoppingStock created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<ShoppingStockResponseDTO> updateShoppingStock(int id, ShoppingStockRequestDTO shoppingStockRequestDTO) {
        Optional<ShoppingStock> shoppingStockOptional = shoppingStockRepository.findById(id);

        if (shoppingStockOptional.isEmpty()) {
            return new ApiResponse<>("ShoppingStock not found", Estatus.ERROR, null);
        } else {
            ShoppingStock shoppingStock = shoppingStockOptional.get();
            modelMapper.map(shoppingStockRequestDTO, shoppingStock);
            shoppingStock.setProductStock(productStockRepository.getProductStockById(shoppingStockRequestDTO.getProductStock()));
            shoppingStock.setShoppingCart(shoppingCartRepository.getShoppingCartById(shoppingStockRequestDTO.getShoppingCart()));
            shoppingStockRepository.save(shoppingStock);
            ShoppingStockResponseDTO response = modelMapper.map(shoppingStock, ShoppingStockResponseDTO.class);
            return new ApiResponse<>("ShoppingStock updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteShoppingStock(int id) {
        Optional<ShoppingStock> shoppingStockOptional = shoppingStockRepository.findById(id);
        if (shoppingStockOptional.isEmpty()) {
            return new ApiResponse<>("Store not found", Estatus.ERROR, null);
        }else {
            shoppingStockRepository.deleteById(id);
            return new ApiResponse<>("Store deleted successfully", Estatus.SUCCESS, null);
        }
    }


}
