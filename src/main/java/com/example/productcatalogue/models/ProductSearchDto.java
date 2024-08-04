package com.example.productcatalogue.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSearchDto {
    private String name;
    private int pageNumber;
    private int pageSize;
}
