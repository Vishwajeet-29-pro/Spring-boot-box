package com.spring.h2.dto;

public record ProductResponse(
        String productName,
        String description,
        double productPrice,
        int productQuantity
){}
