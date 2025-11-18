package com.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Product")
@Data
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer productId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryID")
    private Category category;
    
    @Column(name = "ProductName", nullable = false)
    private String productName;
    
    @Column(name = "Material")
    private String material;
    
    @Column(name = "Price", precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "Quantity")
    private Integer quantity;
    
    @Column(name = "ReleaseDate")
    private LocalDate releaseDate;
}