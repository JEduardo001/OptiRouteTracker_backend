package com.SwSoftware.OptiRouteTracker.utils.mapper;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    DtoCategory toDto(CategoryEntity category);

}
