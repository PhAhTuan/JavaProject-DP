package com.inventory.dto;

import lombok.Data;
import java.util.List;

@Data
public class CategoryWithProductsResponse {
    private String categoryName;
    private List<ProductResponse> products;
}