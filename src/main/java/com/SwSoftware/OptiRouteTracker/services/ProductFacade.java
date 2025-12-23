package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoCreateProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoProduct;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.InventoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import com.SwSoftware.OptiRouteTracker.utils.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;
    private final UserService userService;


    public ProductFacade(ProductService productService, InventoryService inventoryService,CategoryService categoryService,
                         ProductMapper productMapper,UserService userService){
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
        this.userService = userService;
    }


    @Transactional
    public DtoProduct createProduct(DtoCreateProduct data) {

        InventoryEntity inventory =
                inventoryService.getInventoryById(data.getIdInventory());

        List<CategoryEntity> categories =
                categoryService.getCategoriesByIdsOrThrow(data.getIdCategories());

        UserEntity user =
                userService.getUserById(data.getCreatedByUserId());

        DtoProduct productDto = productMapper.dtoCreateProductToDtoProduct(data);
        ProductEntity productEntity = productMapper.toEntity(productDto);

        productEntity.setInventory(inventory);
        productEntity.setCreatedBy(user);
        productEntity.setCategory(new ArrayList<>(categories));

        return productMapper.toDto(productService.saveProduct(productEntity));
    }
}
