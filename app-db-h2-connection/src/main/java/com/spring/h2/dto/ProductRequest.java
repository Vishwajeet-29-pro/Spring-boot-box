package com.spring.h2.dto;

import com.spring.h2.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for creating a product")
public class ProductRequest {

    @Schema(description = "Name of the product", example = "Laptop")
    private String productName;

    @Schema(description = "Description of the product", example = "High-end gaming laptop")
    private String description;

    @Schema(description = "Price of the product", example = "1200.50")
    private double productPrice;

    @Schema(description = "Quantity of the product available", example = "10")
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
