package com.example.productcatalogue.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreProductDto implements Serializable
{
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    private String category;
}
