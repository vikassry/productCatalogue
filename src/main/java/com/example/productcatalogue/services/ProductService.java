package com.example.productcatalogue.services;

import com.example.productcatalogue.clients.FakeStoreClient;
import com.example.productcatalogue.dtos.ProductDto;
import com.example.productcatalogue.models.Category;
import com.example.productcatalogue.models.FakeStoreProductDto;
import com.example.productcatalogue.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public Product getProductById(Long productId) {
        return from(this.fakeStoreClient.getProductById(productId));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] entities  = restTemplateBuilder.build().
                getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();

        for (FakeStoreProductDto entity : entities) {
            products.add(from(entity));
        }

        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        return product;
    }

    private Product from(FakeStoreProductDto dto) {
        Category c = new Category();
        c.setName(dto.getCategory());

        Product product = new Product(dto.getId(), dto.getTitle(), dto.getDescription(), dto.getPrice());
        product.setCategory(c);

        return product;
    }
}
