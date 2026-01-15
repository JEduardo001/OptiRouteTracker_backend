package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product;


import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoCreateProduct {
    @NotNull
    private Long inventory;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Integer quantity;
    private String serialNumber;
    private Integer batch;
    @NotNull
    private boolean active;
    @NotNull
    private Long createdByUserId;
    private List<DtoCategory> categories;
}