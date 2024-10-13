package com.qrmenu.MenuService.domain.model.response;

import lombok.Data;
import java.util.List;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private Long parentCategoryId;
    private List<CategoryResponse> subCategories;
    private List<ProductResponse> products;
}
