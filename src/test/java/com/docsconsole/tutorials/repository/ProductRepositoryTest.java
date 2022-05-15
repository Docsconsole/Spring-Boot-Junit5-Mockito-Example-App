package com.docsconsole.tutorials.repository;

import com.docsconsole.tutorials.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllTest() {
        Product product = new Product();
        product.setProductId(101l);
        product.setProductName("testName");
        product.setProductVendorName("testVendorName");

        productRepository.save(product);

        Iterable<Product> products = productRepository.findAll();
        assertThat(products).isNotEmpty();

        productRepository.deleteAll();
        assertThat(productRepository.findAll()).isEmpty();
    }
}
