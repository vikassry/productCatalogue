package com.example.productcatalogue.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto
{
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    private String category;
}
