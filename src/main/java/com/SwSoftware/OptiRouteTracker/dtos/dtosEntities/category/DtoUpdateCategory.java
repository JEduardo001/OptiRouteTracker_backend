package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoUpdateCategory {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private boolean active;
    @NotNull
    private Integer quantityProducts;
}
