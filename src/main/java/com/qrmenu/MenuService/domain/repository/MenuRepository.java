package com.qrmenu.MenuService.domain.repository;

import com.qrmenu.MenuService.domain.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByRestaurantId(UUID restaurantId);

}
