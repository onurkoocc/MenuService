package com.qrmenu.MenuService.domain.service;

import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.ProductResponse;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(Long id);
}
