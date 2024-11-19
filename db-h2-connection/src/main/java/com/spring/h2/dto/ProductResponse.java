package com.spring.h2.dto;

import com.spring.h2.model.Product;

public record ProductResponse(
        String productName,
        String description,
        double productPrice,
        int productQuantity
){
    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getProductName(),
                product.getDescription(),
                product.getProductPrice(),
                product.getProductQuantity()
        );
    }
}
