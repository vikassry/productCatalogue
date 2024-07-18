package com.example.productcatalogue.controllers;

import com.example.productcatalogue.dtos.CategoryDto;
import com.example.productcatalogue.dtos.ProductDto;
import com.example.productcatalogue.models.Product;
import com.example.productcatalogue.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(from(product));
        }

        return productDtos;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws IllegalArgumentException {
        try {
            if (id < 1) {
                throw new IllegalArgumentException("Id must be a positive integer");
            }
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            Product p = productService.getProductById(id);
            if (p == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(from(p), headers, HttpStatus.OK);
        } catch (IllegalArgumentException ex){
            throw ex;
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ProductDto createProducts(@RequestBody ProductDto product) {
        return product;
    }

    @PutMapping("{id}")
    public ProductDto replaceProducts(@PathVariable Long id, @RequestBody ProductDto product) {
        product.setId(id);
        return product;
    }

    private ProductDto from(Product p){
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setDescription(p.getDescription());

        CategoryDto c = new CategoryDto();
        c.setName(p.getCategory().getName());
        dto.setCategory(c);
        return dto;
    }
}
