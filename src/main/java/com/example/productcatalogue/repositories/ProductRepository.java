package com.example.productcatalogue.repositories;

import com.example.productcatalogue.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);
    List<Product> findAllByPriceBetween(double from, double to);
    List<Product> findAllByIsPrimeSpecific(Boolean isPrimeSpecific);
    List<Product> findAllByIsPrimeSpecificTrue();
    List<Product> findProductsByName(String name, Pageable pageable);
    List<Product> findAllByOrderByPriceDesc();

//    @Query("select c.name from Category c join Product p on p.category.id = c.id where p.id=:productId")
    @Query("select c.name from Category c join Product p on p.category.id = c.id where p.id=?1")
    String findCategoryNameByProductId(Long productId);
}
