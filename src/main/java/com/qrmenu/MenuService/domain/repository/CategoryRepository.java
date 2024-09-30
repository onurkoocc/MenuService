package com.qrmenu.MenuService.domain.repository;

import com.qrmenu.MenuService.domain.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByMenuId(Long menuId);
    List<Category> findByParentCategoryId(Long parentCategoryId);
    List<Category> findByMenu_IdAndParentCategoryIsNull(Long menuId);
}
