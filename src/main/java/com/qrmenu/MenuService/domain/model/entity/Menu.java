package com.qrmenu.MenuService.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID restaurantId;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonManagedReference
    private List<Category> categories;

}
