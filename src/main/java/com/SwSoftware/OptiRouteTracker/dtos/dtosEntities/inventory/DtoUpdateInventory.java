package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoUpdateInventory {
    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String location;
    @NotNull
    private Boolean active;
}
