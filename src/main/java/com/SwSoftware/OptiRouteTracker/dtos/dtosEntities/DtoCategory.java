package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCategory {
    private Long id;
    private String name;
    private boolean active;
    private Integer quantityProducts;
}
