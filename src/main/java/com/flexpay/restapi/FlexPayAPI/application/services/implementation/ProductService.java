package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ProductRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ProductResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IProductService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Product;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IProductRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(IProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<ProductResponseDTO> getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            ProductResponseDTO responseDTO = modelMapper.map(product, ProductResponseDTO.class);
            return new ApiResponse<>("Product fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Product not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<ProductResponseDTO>> getAllProducts() {
        List<Product> productList = (List<Product>) productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOList = productList.stream()
                .map(entity-> modelMapper.map(entity, ProductResponseDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("All products fetched successfully", Estatus.SUCCESS, productResponseDTOList);
    }

    @Override
    public ApiResponse<ProductResponseDTO> createProduct(ProductRequestDTO productRequestDTO) {
        var product = modelMapper.map(productRequestDTO, Product.class);
        productRepository.save(product);
        var response = modelMapper.map(product, ProductResponseDTO.class);
        return new ApiResponse<>("Product created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<ProductResponseDTO> updateProduct(int id, ProductRequestDTO productRequestDTO) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            modelMapper.map(productRequestDTO, product);
            productRepository.save(product);
            ProductResponseDTO responseDTO = modelMapper.map(product, ProductResponseDTO.class);
            return new ApiResponse<>("Product updated successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Product not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<Void> deleteProduct(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return new ApiResponse<>("Store not found", Estatus.ERROR, null);
        }else {
            productRepository.deleteById(id);
            return new ApiResponse<>("Store deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
