package com.example.productcatalogue.services;

import com.example.productcatalogue.dtos.ProductDto;
import com.example.productcatalogue.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long productId);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product updateProduct(Product product);
}
