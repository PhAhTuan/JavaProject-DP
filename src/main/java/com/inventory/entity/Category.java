package com.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Category")
@Data
public class Category {
    
    @Id
    @Column(name = "CategoryID")
    private Integer categoryId;
    
    @Column(name = "CategoryName", nullable = false)
    private String categoryName;
    
    @Column(name = "Description")
    private String description;
}