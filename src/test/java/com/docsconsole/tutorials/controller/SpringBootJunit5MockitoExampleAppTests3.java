package com.docsconsole.tutorials.controller;

import com.docsconsole.tutorials.controllers.ProductsController;
import com.docsconsole.tutorials.model.Product;
import com.docsconsole.tutorials.service.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductsController.class)
public class SpringBootJunit5MockitoExampleAppTests3 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productServiceImpl;
    private List<Product> productList;
    private Product product;
    private String requestJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setProductName("productName1");
        product1.setProductVendorName("productVendor1");
        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setProductName("productName2");
        product2.setProductVendorName("productVendor2");
        this.productList.add(product1);
        this.productList.add(product2);
        product = product1;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        requestJson = ow.writeValueAsString(product);

    }

    @Test
    public void testWeb_WhenSuccessIsResult() throws Exception {
        this.mockMvc.perform(get("/curd-app/api/testWeb")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Result: Success")));
    }

    @Test
    public void givenProducts_whenGetProducts_thenReturnJsonArray() throws Exception {
        given(productServiceImpl.getAllProducts()).willReturn(new ResponseEntity(productList, HttpStatus.OK));
        this.mockMvc.perform(get("/curd-app/api/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("productVendor2")));
    }

    @Test
    public void testSaveProduct_WhenProduct_NotNull() throws Exception {

        this.mockMvc.perform(
                post("/curd-app/api/products")
                        .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

}





