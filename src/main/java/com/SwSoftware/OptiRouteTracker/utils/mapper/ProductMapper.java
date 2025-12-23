package com.SwSoftware.OptiRouteTracker.utils.mapper;

import com.SwSoftware.OptiRouteTracker.dtos.dtosCreate.DtoCreateProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoProduct;
import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class, CategoryMapper.class}
)

public interface ProductMapper {
    DtoProduct toDto(ProductEntity productEntity);
    ProductEntity toEntity(DtoProduct product);
    DtoProduct dtoCreateProductToDtoProduct(DtoCreateProduct product);
}
