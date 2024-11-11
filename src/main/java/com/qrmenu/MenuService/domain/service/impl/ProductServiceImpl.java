package com.qrmenu.MenuService.domain.service.impl;

import com.qrmenu.MenuService.domain.dao.ProductDao;
import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.ProductResponse;
import com.qrmenu.MenuService.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        return productDao.createProduct(request);
    }

    @Override
    public ProductResponse getProductById(Long id){
        return productDao.getProductById(id);
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) {
        return productDao.updateProduct(productRequest);
    }

    @Override
    public void deleteProduct(Long productId) {
        productDao.deleteProduct(productId);
    }
}
