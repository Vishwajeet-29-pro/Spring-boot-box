package com.spring.h2.dto;

import com.spring.h2.model.Product;

public record ProductResponse(
        int productId,
        String productName,
        String description,
        double productPrice,
        int productQuantity
){
    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getProductPrice(),
                product.getProductQuantity()
        );
    }
}
