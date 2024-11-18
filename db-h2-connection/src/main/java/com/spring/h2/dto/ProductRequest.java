package com.spring.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {
    private String productName;
    private String description;
    private String productPrice;
    private String productQuantity;
}
