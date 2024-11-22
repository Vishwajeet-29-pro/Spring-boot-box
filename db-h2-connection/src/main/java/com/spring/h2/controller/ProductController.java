package com.spring.h2.controller;

import com.spring.h2.dto.ProductRequest;
import com.spring.h2.dto.ProductResponse;
import com.spring.h2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.createProduct(productRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponseList = productService.getAllProduct();
        return new ResponseEntity<>(productResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
