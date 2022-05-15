package com.docsconsole.tutorials.service;

import com.docsconsole.tutorials.model.Product;
import com.docsconsole.tutorials.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl {

    @Autowired
    ProductRepository productRepository;


    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity<?> saveProduct(Product product) {
        Product resultedProduct = productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<?> updateProduct(Product product) {
        Product resultedProduct = productRepository.save(product);
        return ResponseEntity.ok(resultedProduct);
    }

    public ResponseEntity<?> deleteProduct(Product product) {
        productRepository.delete(product);
        Map map = new HashMap();
        map.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(map);
    }

    public ResponseEntity<?> patchProduct(Product product) {
        Product resultedProduct = productRepository.save(product);
        return ResponseEntity.ok(resultedProduct);
    }
}
