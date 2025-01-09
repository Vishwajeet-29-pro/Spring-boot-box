package com.spring.relationship.one_to_one.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    int statusCode;
    String message;
}
