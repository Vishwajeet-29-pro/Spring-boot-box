package com.spring.h2.dto;

public record ProductResponse(
        String productName,
        String description,
        String productPrice,
        String productQuantity
){}
