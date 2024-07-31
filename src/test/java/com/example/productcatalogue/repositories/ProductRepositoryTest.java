package com.example.productcatalogue.repositories;

import com.example.productcatalogue.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    void testRepository() {
        Optional<Product> product = productRepository.findById(1L);
        assertTrue(product.isPresent());
    }
}