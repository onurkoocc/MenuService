package com.qrmenu.MenuService.domain.model.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String title;
    private String description;
    private double price;
    private String photoUrl;
}
