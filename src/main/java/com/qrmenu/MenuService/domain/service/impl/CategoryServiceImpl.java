package com.qrmenu.MenuService.domain.service.impl;

import com.qrmenu.MenuService.domain.dao.CategoryDao;
import com.qrmenu.MenuService.domain.model.request.CategoryRequest;
import com.qrmenu.MenuService.domain.model.response.CategoryResponse;
import com.qrmenu.MenuService.domain.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryResponse createCategory(CategoryRequest request) {
        return categoryDao.createCategory(request);
    }

    public CategoryResponse getCategoryById(Long id) {
        return categoryDao.getCategoryById(id);
    }

}
