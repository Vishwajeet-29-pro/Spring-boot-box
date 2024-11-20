package com.spring.h2.service;

import com.spring.h2.dto.ProductRequest;
import com.spring.h2.dto.ProductResponse;
import com.spring.h2.model.Product;
import com.spring.h2.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    public void setup() {
        productService = new ProductServiceImpl(productRepository);
        product = new Product();
        product.setProductName("Mobile");
        product.setDescription("Mobile under 20000");
        product.setProductPrice(19999);
        product.setProductQuantity(100);
    }

    @Test
    public void create_product_should_returns_product(){
        ProductRequest productRequest = new ProductRequest("Mobile","Mobile under 20000",19999, 100);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse productResponse = productService.createProduct(productRequest);

        assertNotNull(productResponse);
        assertEquals("Mobile", productResponse.productName());
    }

    @Test
    public void get_all_should_return_product_list() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<ProductResponse> productResponseList = productService.getAllProduct();

        assertEquals(1, productResponseList.size());
        assertEquals("Mobile", productResponseList.getFirst().productName());
    }
}
