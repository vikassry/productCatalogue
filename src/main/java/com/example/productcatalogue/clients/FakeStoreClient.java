package com.example.productcatalogue.clients;

import com.example.productcatalogue.models.FakeStoreProductDto;
import com.example.productcatalogue.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FakeStoreClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDto getProductById(Long id){
        ResponseEntity<FakeStoreProductDto> entity  = this.restTemplateBuilder.build()
                .getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);
        if (entity.getBody() == null){
            return null;
        }
        return entity.getBody();
    }
}
