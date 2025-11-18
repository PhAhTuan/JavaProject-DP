package com.inventory.service;

import com.inventory.dto.*;
import com.inventory.entity.Category;
import com.inventory.entity.Product;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public ProductResponse getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return toResponse(product);
    }
    
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setMaterial(request.getMaterial());
        product.setCategory(category);
        product.setReleaseDate(LocalDate.now());
        
        Product saved = productRepository.save(product);
        return toResponse(saved);
    }
    
    @Transactional
    public ProductResponse updateProduct(Integer id, ProductRequest request) {
            Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setMaterial(request.getMaterial());
        product.setCategory(category);
        
        Product updated = productRepository.save(product);
        return toResponse(updated);
    }
    
    @Transactional
    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
        productRepository.deleteById(id);
    }
    
    public List<CategoryWithProductsResponse> searchProducts(String name, String category) {
        String n = (name == null || name.isEmpty()) ? "" : name;
        String c = (category == null || category.isEmpty()) ? "" : category;
        
        List<Product> products;
        if (!n.isEmpty() && !c.isEmpty()) {
            products = productRepository.findByProductNameContainingIgnoreCaseAndCategory_CategoryNameContainingIgnoreCase(n, c);
        } else if (!n.isEmpty()) {
            products = productRepository.findByProductNameContainingIgnoreCase(n);
        } else if (!c.isEmpty()) {
            products = productRepository.findByCategory_CategoryNameContainingIgnoreCase(c);
        } else {
            products = productRepository.findAll();
        }
        
        Map<String, List<ProductResponse>> grouped = products.stream()
                .map(this::toResponse)
                .collect(Collectors.groupingBy(ProductResponse::getCategoryName));
        
        return grouped.entrySet().stream()
                .map(entry -> {
                    CategoryWithProductsResponse response = new CategoryWithProductsResponse();
                    response.setCategoryName(entry.getKey());
                    response.setProducts(entry.getValue());
                    return response;
                })
                .collect(Collectors.toList());
    }
    
    private ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setMaterial(product.getMaterial());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setReleaseDate(product.getReleaseDate());
        response.setCategoryName(product.getCategory() != null ? product.getCategory().getCategoryName() : null);
        return response;
    }
}