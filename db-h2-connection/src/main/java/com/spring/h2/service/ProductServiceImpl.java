package com.spring.h2.service;

import com.spring.h2.dto.ProductRequest;
import com.spring.h2.dto.ProductResponse;
import com.spring.h2.model.Product;
import com.spring.h2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product savedProduct = productRepository.save(ProductRequest.toEntity(productRequest));
        return ProductResponse.toProductResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(ProductResponse::toProductResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(int productId) {
        Product product = productRepository.getReferenceById(productId);
        return ProductResponse.toProductResponse(product);
    }

    @Override
    public ProductResponse updateProductById(int id, ProductRequest productRequest) {
        return null;
    }

    @Override
    public void deleteProductById(int id) {

    }
}
