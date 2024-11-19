package com.spring.h2.dto;

import com.spring.h2.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String description;
    private double productPrice;
    private int productQuantity;

    public static Product toEntity(ProductRequest productRequest) {
        Product product = new Product();
                product.setProductName(productRequest.getProductName());
                product.setDescription(productRequest.getDescription());
                product.setProductPrice(productRequest.getProductPrice());
                product.setProductQuantity(productRequest.getProductQuantity());
        return product;
    }
}
