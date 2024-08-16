package com.example.productcatalogue.services;

import com.example.productcatalogue.clients.FakeStoreClient;
import com.example.productcatalogue.models.Category;
import com.example.productcatalogue.models.FakeStoreProductDto;
import com.example.productcatalogue.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient, RedisTemplate<String, Object> productRedisTemplate){
//        this.restTemplateBuilder = restTemplateBuilder;
//        this.fakeStoreClient = fakeStoreClient;
//        this.productRedisTemplate = productRedisTemplate;
//    }

    @Override
    public Product getProductById(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = null;

        fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get("PRODUCTS", productId);

        if (fakeStoreProductDto != null) {
            System.out.println("found product in cache");
            return from(fakeStoreProductDto);
        }

        fakeStoreProductDto = this.fakeStoreClient.getProductById(productId);

        System.out.println("fetched from fake store, saving to cache...");
        redisTemplate.opsForHash().put("PRODUCTS", productId, fakeStoreProductDto);

        return from(fakeStoreProductDto);
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
        Product p = new Product();
        p.setId(dto.getId());
        p.setName(dto.getTitle());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setImageUrl(dto.getImageUrl());

        Category c = new Category();
        p.setCategory(c);

        return p;
    }

}
