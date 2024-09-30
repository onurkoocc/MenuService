package com.qrmenu.MenuService.domain.repository;

import com.qrmenu.MenuService.domain.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
