package com.qrmenu.MenuService.domain.model.response;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class MenuResponse {
    private Long id;
    private UUID restaurantId;
    private List<CategoryResponse> categories;
}
