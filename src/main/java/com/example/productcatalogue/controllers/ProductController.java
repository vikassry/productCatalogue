package com.example.productcatalogue.controllers;

import com.example.productcatalogue.dtos.CategoryDto;
import com.example.productcatalogue.dtos.ProductDto;
import com.example.productcatalogue.models.Category;
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
    public ProductDto createProducts(@RequestBody ProductDto productDto) {
        Product product = productService.createProduct(from(productDto));
        return from(product);
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

    private Product from(ProductDto p){
        Product product = new Product();
        product.setId(p.getId());
        product.setName(p.getName());
        product.setPrice(p.getPrice());
        product.setDescription(p.getDescription());

        if (p.getCategory() != null) {
            Category c = new Category();
            c.setId(p.getCategory().getId());
            c.setName(p.getCategory().getName());
            product.setCategory(c);
        }
        return product;
    }
}

/*
curl --request POST \
        --url http://localhost:8080/products \
        --header 'Content-Type: application/json' \
        --data '{"id":1, "name":"IPhone15","description":"Stash your iphone everyday","price":109.95,"category":{"id":1,"name":"iphone"}}
        '

curl --request POST \
  --url https://api-gateway.payretailers.com/v2/paywalls \
  --header 'Authorization: Basic MTIzNDU4MjI6OTYwZTNhNWIxYTE2ZGFiNTRmODBjMDVkNWZjODQzNzU0NjNjMDdlNDljODUyZTA0YWFmMDgzNjg2ZDI0M2E5ZQ==' \
  --header 'Content-Type: application/json' \
  --data '{
	"amount":"50",
	"currency":"BRL",
	"description":"alpari test 1",
	"trackingId":"trx 11286571",
	"notificationUrl":"https://money.dev.exinity.io/psp/callback",
	"returnUrl":"https://trade-fxtm.dev.exinity.io",
	"cancelUrl":"https://trade-fxtm.dev.exinity.io",
	"paymentChannelTypeCode":"ONLINE",
	"customer": {
		"firstName":"Vitor De Goes",
		"lastName":"Teixeira",
		"email":"vitorgt1001@gmail.com",
		"country":"BR",
		"phone":"5511948780146",
		"ip":"2804:14d:7e2b:8b68:c86c:f384:ad88:3246",
		"address":"Street: Rua dos Ipes 1821 Cidade Jardim, subStreet: Apto 21,State:SP,City: Caraguatatuba, PostCode: 11664270"
	},
	"language":"pt"
}
'

 */