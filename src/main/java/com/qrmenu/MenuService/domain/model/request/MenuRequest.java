package com.qrmenu.MenuService.domain.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class MenuRequest {
    @NotNull(message = "Restaurant ID is required")
    private UUID restaurantId;

    private List<CategoryRequest> categories;
}
