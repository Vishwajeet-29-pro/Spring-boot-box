package com.spring.h2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.h2.dto.ProductRequest;
import com.spring.h2.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductRequest productRequest;
    private ProductResponse productResponse;

    @BeforeEach
    public void setup() {
        productRequest = new ProductRequest("Laptop", "High-end gaming laptop", 1500.0, 10);
        productResponse = new ProductResponse(1, "Laptop", "High-end gaming laptop", 1500.0, 10);
    }

    @Test
    public void create_should_return_product_and_201_created() throws Exception {
        when(productService.createProduct(any(ProductRequest.class))).thenReturn(productResponse);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productName").value("Laptop"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void get_all_should_return_list_of_all_product() throws Exception {
        when(productService.getAllProduct()).thenReturn(Collections.singletonList(productResponse));

        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void get_by_id_should_return_single_product_with_given_id() throws Exception {
        int productId = 1;
        when(productService.getProductById(productId)).thenReturn(productResponse);

        mockMvc.perform(get("/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.productName").value("Laptop"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void update_by_id_should_update_product_and_return_product() throws Exception {
        int productId = 1;
        ProductRequest updateRequest = new ProductRequest("Updated Laptop", "Updated description", 1800.0, 8);
        ProductResponse updatedProductResponse = new ProductResponse(productId, "Updated Laptop", "Updated description", 1800.0, 8);

        when(productService.updateProductById(eq(productId), any(ProductRequest.class))).thenReturn(updatedProductResponse);

        mockMvc.perform(put("/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.productName").value("Updated Laptop"));
    }
}
