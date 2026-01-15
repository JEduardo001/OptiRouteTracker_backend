package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoProduct;
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
public class DtoInventoryWithProducts {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private String location;
    private Integer quantity;
    private Boolean active;
    private List<DtoProduct> products;
}
