package com.spring.h2.service;

import com.spring.h2.dto.ProductRequest;
import com.spring.h2.dto.ProductResponse;
import com.spring.h2.exception.ProductNotFoundException;
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
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product with Id "+productId+" not found")
        );
        return ProductResponse.toProductResponse(product);
    }

    @Override
    public ProductResponse updateProductById(int id, ProductRequest productRequest) {
        Product toUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id "+id+" not found"));
        toUpdate.setProductName(productRequest.getProductName());
        toUpdate.setDescription(productRequest.getDescription());
        toUpdate.setProductPrice(productRequest.getProductPrice());
        toUpdate.setProductQuantity(productRequest.getProductQuantity());

        Product updatedProduct = productRepository.save(toUpdate);
        return ProductResponse.toProductResponse(updatedProduct);
    }

    @Override
    public void deleteProductById(int id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
