package com.docsconsole.tutorials.utils;

import com.docsconsole.tutorials.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static List getProducts() throws IOException {
        InputStream inJson = Product.class.getResourceAsStream("/product.json");
        Product product = new ObjectMapper().readValue(inJson, Product.class);

        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }
}
