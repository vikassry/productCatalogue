package com.example.productcatalogue.services;

import com.example.productcatalogue.models.Product;
import com.example.productcatalogue.models.ProductSearchDto;
import com.example.productcatalogue.models.SortDirection;
import com.example.productcatalogue.models.SortParam;
import com.example.productcatalogue.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SearchService {

    @Autowired
    private ProductRepository productRepo;

    public Page<Product> SearchProduct(String name, int pageNumber, int pageSize, List<SortParam> sortParams) {
        Sort sort = null;
        if (sortParams != null && !sortParams.isEmpty()) {
            SortParam param = sortParams.get(0);
            if (param.getDirection() == SortDirection.DESC){
                sort = Sort.by(param.getField()).descending();
            } else{
                sort = Sort.by(param.getField());
            }

            for (int i = 1; i < sortParams.size(); i++) {
                if (param.getDirection() == SortDirection.DESC){
                    sort = sort.and(Sort.by(sortParams.get(i).getField()).descending());
                } else{
                    sort = sort.and(Sort.by(sortParams.get(i).getField()));
                }
            }
        }

        return productRepo.findProductsByName(
                name,
                PageRequest.of(pageNumber, pageSize, sort)
        );
    }
}
