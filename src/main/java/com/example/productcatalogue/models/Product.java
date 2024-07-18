package com.example.productcatalogue.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;
    private boolean isPrime;

    public Product(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = "http://www.google.com/images/branding/google_logo.png";
    }
}
