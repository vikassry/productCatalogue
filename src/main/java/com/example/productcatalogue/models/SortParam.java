package com.example.productcatalogue.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String field;
    private SortDirection direction;
}
