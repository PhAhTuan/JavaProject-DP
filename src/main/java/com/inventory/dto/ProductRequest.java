package com.inventory.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    
    @NotBlank(message = "Product name is required")
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9\\s]{2,50}$", 
             message = "Product name must start with uppercase and be 3-51 characters")
    private String productName;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;
    
    @NotNull(message = "Category ID is required")
    private Integer categoryId;
    
    private String material;
}