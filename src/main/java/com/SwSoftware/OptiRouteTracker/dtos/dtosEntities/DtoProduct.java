package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities;

import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.InventoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoProduct {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private Integer quantity;
    private String serialNumber;
    private Integer batch;
    private boolean active;
    private UserEntity createdBy;
    private List<CategoryEntity> category;
}
