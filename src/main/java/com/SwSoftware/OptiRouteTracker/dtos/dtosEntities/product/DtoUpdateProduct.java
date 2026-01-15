package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoUpdateProduct {
    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Integer quantity;
    private String serialNumber;
    private Integer batch;
    @NotNull
    private Long inventory;
    @NotNull
    private boolean active;
    private List<DtoCategory> categories;
}
