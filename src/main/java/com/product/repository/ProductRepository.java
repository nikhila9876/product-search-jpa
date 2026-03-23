package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Derived queries
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);

    // JPQL queries

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> getProductsSorted();

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> getExpensiveProducts(double price);

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> getByCategoryJPQL(String category);
}