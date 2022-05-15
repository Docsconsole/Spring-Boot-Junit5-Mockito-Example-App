package com.docsconsole.tutorials.service;


import com.docsconsole.tutorials.model.Product;
import com.docsconsole.tutorials.repository.ProductRepository;
import com.docsconsole.tutorials.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productServiceImpl;
    @Mock
    ProductRepository productRepository;

    List productsExpected;
    Product productExpected;

    @BeforeEach
    public void setUp() throws IOException {
        productsExpected = TestUtils.getProducts();
        productExpected = (Product) productsExpected.get(0);
    }


    @Test
    public void getAllProductsTest() throws IOException {

        when(productRepository.findAll()).thenReturn(productsExpected);
        ResponseEntity responseEntityExpected  = productServiceImpl.getAllProducts();
        Object objectReal = responseEntityExpected.getBody();
        Product productReal = null;
        if(objectReal instanceof List){
            List productsReal = (List)objectReal;
            productReal = (Product) productsReal.get(0);
        }
        assertNotNull(productReal);
        assert(productReal.getProductVendorName().equals(productExpected.getProductVendorName()));

    }
}
