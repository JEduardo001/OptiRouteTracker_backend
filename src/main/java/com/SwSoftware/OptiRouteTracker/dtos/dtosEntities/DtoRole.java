package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoRole {
    private Long id;
    private String name;
    private boolean active;
}
