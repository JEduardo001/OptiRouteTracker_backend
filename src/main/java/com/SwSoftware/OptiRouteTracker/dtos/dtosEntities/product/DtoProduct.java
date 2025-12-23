package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoProduct {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private Integer quantity;
    private String serialNumber;
    private Integer batch;
    private boolean active;
    private DtoUser createdBy;
    private List<DtoCategory> category;
}
