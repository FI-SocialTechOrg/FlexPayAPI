package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ProductStockRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ProductStockResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IProductStockService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.ProductStock;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IProductRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IProductStockRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IStateStockRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IStoreRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductStockService implements IProductStockService {
    private final IProductStockRepository productStockRepository;
    private final IProductRepository productRepository;
    private final IStoreRepository storeRepository;
    private final IStateStockRepository stateStockRepository;
    private final ModelMapper modelMapper;

    public ProductStockService(IProductStockRepository productStockRepository, IProductRepository productRepository, IStoreRepository storeRepository, IStateStockRepository stateStockRepository, ModelMapper modelMapper) {
        this.productStockRepository = productStockRepository;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.stateStockRepository = stateStockRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ApiResponse<ProductStockResponseDTO> getProductStockById(int id) {
        Optional<ProductStock> productStockOptional = productStockRepository.findById(id);
        if (productStockOptional.isPresent()){
            ProductStock productStock = productStockOptional.get();
            ProductStockResponseDTO responseDTO = modelMapper.map(productStock, ProductStockResponseDTO.class);
            return new ApiResponse<>("ProductStock fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("ProductStock not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<ProductStockResponseDTO>> getAllProductStocks() {
        List<ProductStock> productStockList = (List<ProductStock>) productStockRepository.findAll();
        List<ProductStockResponseDTO> productStockResponseDTOList = productStockList.stream()
                .map(entity-> modelMapper.map(entity, ProductStockResponseDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("All productStocks fetched successfully", Estatus.SUCCESS, productStockResponseDTOList);
    }
    @Override
    public ApiResponse<ProductStockResponseDTO> createProductStock(ProductStockRequestDTO productStockRequestDTO) {
        var productStock = modelMapper.map(productStockRequestDTO, ProductStock.class);
        productStock.setProduct(productRepository.getProductById(productStockRequestDTO.getProduct()));
        productStock.setStore(storeRepository.getStoreById(productStockRequestDTO.getStore()));
        productStock.setStateStock(stateStockRepository.getStateStockById(productStockRequestDTO.getStateStock()));
        productStockRepository.save(productStock);
        var response = modelMapper.map(productStock, ProductStockResponseDTO.class);
        return new ApiResponse<>("ProductStock created successfully", Estatus.SUCCESS, response);
    }
    @Override
    public ApiResponse<ProductStockResponseDTO> updateProductStock(int id, ProductStockRequestDTO productStockRequestDTO) {
        Optional<ProductStock> productStockOptional = productStockRepository.findById(id);
        if (productStockOptional.isPresent()){
            ProductStock productStock = productStockOptional.get();
            modelMapper.map(productStockRequestDTO, productStock);
            productStock.setProduct(productRepository.getProductById(productStockRequestDTO.getProduct()));
            productStock.setStore(storeRepository.getStoreById(productStockRequestDTO.getStore()));
            productStock.setStateStock(stateStockRepository.getStateStockById(productStockRequestDTO.getStateStock()));
            productStockRepository.save(productStock);
            ProductStockResponseDTO responseDTO = modelMapper.map(productStock, ProductStockResponseDTO.class);
            return new ApiResponse<>("ProductStock updated successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("ProductStock not found", Estatus.ERROR, null);
        }
    }
    @Override
    public ApiResponse<Void> deleteProductStock(int id) {
        Optional<ProductStock> productStockOptional = productStockRepository.findById(id);
        if (productStockOptional.isEmpty()) {
            return new ApiResponse<>("Store not found", Estatus.ERROR, null);
        }else {
            productStockRepository.deleteById(id);
            return new ApiResponse<>("Store deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
