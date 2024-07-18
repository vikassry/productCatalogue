package com.example.productcatalogue.dtos;

import com.example.productcatalogue.models.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private CategoryDto category;
}
