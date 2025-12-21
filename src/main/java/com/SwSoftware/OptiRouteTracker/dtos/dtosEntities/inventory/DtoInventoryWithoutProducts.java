package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoInventoryWithoutProducts {
    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String name;
    private String description;
    private LocalDateTime createDate;
    private String location;
    private Integer quantity;
}
