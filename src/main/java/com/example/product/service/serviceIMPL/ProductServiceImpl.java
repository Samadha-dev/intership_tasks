package com.example.product.service.serviceIMPL;

import com.example.product.dto.ProductRequestDTO;
import com.example.product.dto.ProductResponseDTO;
import com.example.product.entity.ProductEntity;
import com.example.product.repo.ProductRepository;
import com.example.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        ProductEntity entity = modelMapper.map(request, ProductEntity.class);
        ProductEntity saved = productRepository.save(entity);
        return modelMapper.map(saved, ProductResponseDTO.class);
    }


    @Override
    public ProductResponseDTO getProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return modelMapper.map(product, ProductResponseDTO.class);
    }


    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        modelMapper.map(request, product); // update fields
        ProductEntity updated = productRepository.save(product);
        return modelMapper.map(updated, ProductResponseDTO.class);
    }


    @Override
    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }


    @Override
    public Map<String, Object> getProducts(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<ProductEntity> productPage;

        if (search == null || search.isEmpty()) {
            productPage = productRepository.findAll(pageable);
        } else {
            // Custom search by name or brand (case-insensitive)
            productPage = productRepository.findByNameContainingIgnoreCaseOrBrandContainingIgnoreCase(search, search, pageable);
        }

        List<ProductResponseDTO> productDTOs = productPage.getContent()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("products", productDTOs);
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());

        return response;
    }
}
