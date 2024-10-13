package com.qrmenu.MenuService.domain.dao;

import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.ProductResponse;

public interface ProductDao {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse getProductById(Long productId);
}
