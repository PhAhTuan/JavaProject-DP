package com.inventory.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductResponse {
    private Integer productId;
    private String productName;
    private String material;
    private BigDecimal price;
    private Integer quantity;
    private LocalDate releaseDate;
    private String categoryName;
}