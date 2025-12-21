package com.SwSoftware.OptiRouteTracker.utils.mapper;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.DtoCategory;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    DtoCategory toDtoByEntity(CategoryEntity category);
}
