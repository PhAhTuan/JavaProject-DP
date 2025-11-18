package com.inventory.repository;

import com.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    List<Product> findByProductNameContainingIgnoreCase(String name);
    
    List<Product> findByCategory_CategoryNameContainingIgnoreCase(String categoryName);
    
    List<Product> findByProductNameContainingIgnoreCaseAndCategory_CategoryNameContainingIgnoreCase(
        String name, String categoryName
    );
}