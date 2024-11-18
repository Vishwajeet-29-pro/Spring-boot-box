package com.spring.h2.service;

import com.spring.h2.dto.ProductRequest;
import com.spring.h2.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProduct();
    ProductResponse getProductById(int productId);
    ProductResponse updateProductById(int id, ProductRequest productRequest);
    void deleteProductById(int id);
}
