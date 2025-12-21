package com.SwSoftware.OptiRouteTracker.utils.mapper;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.DtoProduct;
import com.SwSoftware.OptiRouteTracker.entities.InventoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class})
public interface ProductMapper {
    @BeanMapping(builder = @Builder(disableBuilder = true))
    DtoProduct toDto(ProductEntity productEntity);
}
