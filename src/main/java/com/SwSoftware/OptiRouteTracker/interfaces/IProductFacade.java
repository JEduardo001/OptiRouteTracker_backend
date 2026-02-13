package com.SwSoftware.OptiRouteTracker.interfaces;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoCreateProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoUpdateProduct;
import org.springframework.transaction.annotation.Transactional;

public interface IProductFacade {
    @Transactional
    DtoProduct createProduct(DtoCreateProduct data);
    @Transactional
    DtoProduct updateProduct(DtoUpdateProduct product);
}
