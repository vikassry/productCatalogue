package com.example.productcatalogue.dtos;

import com.example.productcatalogue.models.BaseModel;
import com.example.productcatalogue.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto extends BaseModel {
    private String name;
}
