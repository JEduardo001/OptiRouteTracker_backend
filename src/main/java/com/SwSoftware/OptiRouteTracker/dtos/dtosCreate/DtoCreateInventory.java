package com.SwSoftware.OptiRouteTracker.dtos.dtosCreate;

import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
