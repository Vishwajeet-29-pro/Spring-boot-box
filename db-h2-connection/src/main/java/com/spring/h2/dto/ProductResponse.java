package com.spring.h2.dto;

import com.spring.h2.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse{
    private int id;
    private String productName;
    private String description;
    private double productPrice;
    private int productQuantity;

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
