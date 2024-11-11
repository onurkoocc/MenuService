package com.qrmenu.MenuService.domain.service;

import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProductById(Long id);
    ProductResponse updateProduct(ProductRequest productRequest);
    void deleteProduct(Long productId);
}
