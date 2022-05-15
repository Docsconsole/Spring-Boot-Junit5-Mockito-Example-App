package com.docsconsole.tutorials.controllers;

import com.docsconsole.tutorials.model.Product;
import com.docsconsole.tutorials.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curd-app/api")
public class ProductsController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("/testWeb")
    public String testWeb() {
        return "Result: Success";
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return productServiceImpl.getAllProducts();
    }

    @PostMapping(value = "/products")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        return productServiceImpl.saveProduct(product);
    }

    @PutMapping(value = "/products")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return productServiceImpl.updateProduct(product);
    }

    @DeleteMapping(value = "/products")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product) {
        return productServiceImpl.deleteProduct(product);
    }
    @PatchMapping(value = "/products")
    public ResponseEntity<?>patchProduct(@RequestBody Product product) {
        return productServiceImpl.patchProduct(product);
    }

}
