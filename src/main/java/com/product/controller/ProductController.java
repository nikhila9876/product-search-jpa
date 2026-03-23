package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.product.entity.Product;
import com.product.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    // Insert sample data
    @PostMapping("/add")
    public String addProduct(@RequestBody Product p) {
        repo.save(p);
        return "Product added";
    }

    // Category search
    @GetMapping("/category/{category}")
    public List<Product> byCategory(@PathVariable String category) {
        return repo.findByCategory(category);
    }

    // Price filter
    @GetMapping("/filter")
    public List<Product> filter(@RequestParam double min,
                               @RequestParam double max) {
        return repo.findByPriceBetween(min, max);
    }

    // Sorted
    @GetMapping("/sorted")
    public List<Product> sorted() {
        return repo.getProductsSorted();
    }

    // Expensive products
    @GetMapping("/expensive/{price}")
    public List<Product> expensive(@PathVariable double price) {
        return repo.getExpensiveProducts(price);
    }
}