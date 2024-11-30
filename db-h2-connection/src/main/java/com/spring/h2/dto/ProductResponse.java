package com.spring.h2.dto;

import com.spring.h2.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for retrieving product details")
public class ProductResponse {

    @Schema(description = "Unique identifier of the product", example = "1")
    private int id;

    @Schema(description = "Name of the product", example = "Laptop")
    private String productName;

    @Schema(description = "Description of the product", example = "High-end gaming laptop")
    private String description;

    @Schema(description = "Price of the product", example = "1200.50")
    private double productPrice;

    @Schema(description = "Quantity of the product available", example = "10")
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
