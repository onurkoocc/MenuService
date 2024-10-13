package com.qrmenu.MenuService.domain.service.impl;

import com.qrmenu.MenuService.domain.dao.ProductDao;
import com.qrmenu.MenuService.domain.model.entity.Product;
import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.ProductResponse;
import com.qrmenu.MenuService.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public ProductResponse create(ProductRequest request) {
        return productDao.createProduct(request);
    }

    @Override
    public ProductResponse getById(Long id){
        return productDao.getProductById(id);
    }
}
