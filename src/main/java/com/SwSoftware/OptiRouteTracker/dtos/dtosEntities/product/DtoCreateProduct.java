package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product;


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
    @Positive
    private Long idInventory;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    @NotNull
    private Integer quantity;
    private String serialNumber;
    private Integer batch;
    @NotNull
    private boolean active;
    @NotNull
    private Long createdByUserId;
    private List<Long> idCategories;
}