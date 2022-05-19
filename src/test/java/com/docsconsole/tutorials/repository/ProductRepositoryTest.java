package com.docsconsole.tutorials.repository;

import com.docsconsole.tutorials.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void saveProduct_success(){
        Product product = new Product();
        product.setProductId(101l);
        product.setProductName("testName");
        product.setProductVendorName("testVendorName");
        Product productResult = productRepository.save(product);
        assertThat(productResult).isNotNull();
    }

    @Test
    void saveAll_success() {
        List<Product> products = Arrays.asList(
                new Product(1001L,"product1", "vendor1"),
                new Product(1002L,"product2", "vendor2"),
                new Product(1003L,"product3", "vendor3")
        );
        Iterable<Product> allProducts = productRepository.saveAll(products);
        assertThat(allProducts).hasSize(3);
    }

    @Test
    public void findAll_success() {
        Iterable<Product> products = productRepository.findAll();
        assertThat(products).isNotEmpty();
    }
    @Test
    public void deleteAll_success() {
        productRepository.deleteAll();
        assertThat(productRepository.findAll()).isEmpty();
    }
}
