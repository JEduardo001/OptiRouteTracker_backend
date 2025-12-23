package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoCreateRole {
    @NotBlank
    private String name;
    @NotNull
    private boolean active;
}
