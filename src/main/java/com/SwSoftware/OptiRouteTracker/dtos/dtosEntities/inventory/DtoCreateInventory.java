package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoCreateInventory {
    @NotBlank
    private String name;
    private String description;
    private String location;
}
