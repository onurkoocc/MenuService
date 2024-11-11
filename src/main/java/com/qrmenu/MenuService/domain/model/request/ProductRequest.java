package com.qrmenu.MenuService.domain.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {
    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;
    private Long categoryId;
    private String title;
    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    private String photoUrl;
}
