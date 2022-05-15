package com.docsconsole.tutorials.controller;

import com.docsconsole.tutorials.controllers.ProductsController;
import com.docsconsole.tutorials.model.Product;
import com.docsconsole.tutorials.service.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
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

    @Test
    public void testWeb_WhenSuccessIsResult() throws Exception {
        this.mockMvc.perform(get("/curd-app/api/testWeb")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Result: Success")));
    }

    @Test
    public void givenProducts_whenGetProducts_thenReturnJsonArray() throws Exception {
        this.mockMvc.perform(get("/curd-app/api/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveProduct_WhenProduct_NotNull() throws Exception {

        Product product = new Product();
        product.setProductId(10000002l);
        product.setProductName("TestProduct");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(product);

        this.mockMvc.perform(
                post("/curd-app/api/products")
                        .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());


    }


}


