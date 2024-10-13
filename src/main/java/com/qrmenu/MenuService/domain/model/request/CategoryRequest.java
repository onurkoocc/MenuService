package com.qrmenu.MenuService.domain.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class CategoryRequest {
    @NotBlank(message = "Category name is required")
    private String name;
    private Long menuId;
    private Long parentCategoryId; // For subcategories

    private List<CategoryRequest> subCategories;
    private List<ProductRequest> products;
}
