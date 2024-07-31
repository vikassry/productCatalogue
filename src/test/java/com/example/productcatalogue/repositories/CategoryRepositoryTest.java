package com.example.productcatalogue.repositories;

import com.example.productcatalogue.models.Category;
import com.example.productcatalogue.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void findCategoryById() {
        Optional<Category> category = categoryRepository.findById(1L);
        assertEquals(1L, category.get().getId());

        Product p = category.get().getProduct().get(0);
        assertEquals(1L, p.getId());
    }
}
