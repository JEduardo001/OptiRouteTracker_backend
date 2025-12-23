package com.SwSoftware.OptiRouteTracker.utils.mapper;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithProducts;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithoutProducts;
import com.SwSoftware.OptiRouteTracker.entities.InventoryEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, CategoryMapper.class})
public interface InventoryMapper {

    DtoInventoryWithoutProducts toDtoByEntity(InventoryEntity inventory);

    DtoInventoryWithoutProducts toDtoWithoutProducts(InventoryEntity inventory);

    DtoInventoryWithProducts dtoToDtoWithProducts(DtoInventoryWithoutProducts inventory);

}
