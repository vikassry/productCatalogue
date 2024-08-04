package com.example.productcatalogue.controllers;

import com.example.productcatalogue.models.Product;
import com.example.productcatalogue.models.ProductSearchDto;
import com.example.productcatalogue.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping()
    public List<Product> search(@RequestBody ProductSearchDto dto){
        return searchService.SearchProduct(dto);
    }
}
