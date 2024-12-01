package com.spring.h2.service;

import com.spring.h2.dto.ProductRequest;
import com.spring.h2.dto.ProductResponse;
import com.spring.h2.exception.ProductNotFoundException;
import com.spring.h2.model.Product;
import com.spring.h2.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        assertEquals("Mobile", productResponse.getProductName());
    }

    @Test
    public void get_all_should_return_product_list() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<ProductResponse> productResponseList = productService.getAllProduct();

        assertEquals(1, productResponseList.size());
        assertEquals("Mobile", productResponseList.getFirst().getProductName());
    }

    @Test
    public void get_by_id_should_return_one_product() {
        when(productRepository.findById(any(Integer.class))).thenReturn(Optional.of(product));

        ProductResponse productResponse = productService.getProductById(product.getId());

        assertEquals("Mobile", productResponse.getProductName());
    }

    @Test
    public void when_product_not_found_should_throw_product_not_found_exception() {
        int id = 33;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(
                ProductNotFoundException.class,
                () -> productService.getProductById(id)
        );
        assertEquals("Product with Id 33 not found", exception.getMessage());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    public void update_by_id_should_update_product_return_updated_product() {
        ProductRequest productRequest = new ProductRequest("Mobile","Mobile under 20000",19999, 90);
        when(productRepository.getReferenceById(any(Integer.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        product.setProductQuantity(90);

        ProductResponse updatedProduct = productService.updateProductById(product.getId(), productRequest);
        assertEquals(90, updatedProduct.getProductQuantity());
    }

    @Test
    public void delete_by_id_should_delete_product() {
        int productId = 1;
        when(productRepository.existsById(productId)).thenReturn(true);

        productService.deleteProductById(productId);

        verify(productRepository, times(1)).deleteById(productId);

    }
}
