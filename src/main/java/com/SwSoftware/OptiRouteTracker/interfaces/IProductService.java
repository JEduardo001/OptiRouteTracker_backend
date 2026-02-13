package com.SwSoftware.OptiRouteTracker.interfaces;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoProduct;
import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface IProductService {
    DtoPageableResponse<DtoProduct> getAllProducts(Integer page, Integer size);
    DtoProduct getProduct(Long idProduct);
    ProductEntity saveProduct(ProductEntity product);
    Page<ProductEntity> getProductsByIdInventory(Long id, Pageable page);
    ProductEntity getProductById(Long idProduct);
    void existProduct(Long idProduct);
    boolean existsByNameAndIdNot(String name,Long idProduct);
    boolean existsBySerialNumberAndIdNot(String serialNumber,Long idProduct);
    @Transactional
    void deleteProduct(Long idProduct, Long idInventory);
}
