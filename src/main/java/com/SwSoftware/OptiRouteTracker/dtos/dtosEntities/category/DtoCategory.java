package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category;

import lombok.*;
import lombok.experimental.Accessors;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoCategory {
    private Long id;
    private String name;
    private boolean active;
    private Integer quantityProducts;
}