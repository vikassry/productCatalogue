package com.example.productcatalogue.services;

import com.example.productcatalogue.models.Product;
import com.example.productcatalogue.models.ProductSearchDto;
import com.example.productcatalogue.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> SearchProduct(ProductSearchDto dto) {
        return productRepo.findByName(dto.getName());
    }
}
