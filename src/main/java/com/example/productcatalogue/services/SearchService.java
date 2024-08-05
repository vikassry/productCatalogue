package com.example.productcatalogue.services;

import com.example.productcatalogue.models.Product;
import com.example.productcatalogue.models.ProductSearchDto;
import com.example.productcatalogue.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepository productRepo;

    public Page<Product> SearchProduct(ProductSearchDto dto) {
        return productRepo.findProductsByName(
                dto.getName(),
                PageRequest.of(dto.getPageNumber(), dto.getPageSize())
        );
    }
}
