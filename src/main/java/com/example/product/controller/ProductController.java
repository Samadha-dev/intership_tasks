package com.example.product.controller;

import com.example.product.dto.ProductRequestDTO;
import com.example.product.dto.ProductResponseDTO;
import com.example.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")  // allows frontend apps (like React) to call APIs without CORS issues
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //  Create Product
    @PostMapping("/batch")
    public List<ProductResponseDTO> createProducts(@RequestBody List<ProductRequestDTO> dtos) {
        return dtos.stream()
                .map(productService::createProduct)
                .collect(Collectors.toList());
    }


    //  Get Product by ID
    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    //  Get All Products (with search + pagination)
    @GetMapping
    public Map<String, Object> getProducts(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getProducts(search, page, size);
    }

    //  Update Product by ID
    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDTO dto) {
        return productService.updateProduct(id, dto);
    }

    //  Delete Product by ID
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully with id: " + id;
    }

}
