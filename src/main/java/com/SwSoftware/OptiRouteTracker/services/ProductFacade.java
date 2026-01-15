package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoCreateProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoProduct;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.product.DtoUpdateProduct;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.InventoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductNameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductSerialNumberAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.utils.mapper.ProductMapper;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                inventoryService.getInventoryById(data.getInventory());

        List<CategoryEntity> categories =
                categoryService.getCategoriesByIdsOrThrow(data.getCategories());

        UserEntity user =
                userService.getUserById(data.getCreatedByUserId());

        ProductEntity productEntity = ProductEntity.builder()
                .name(data.getName())
                .description(data.getDescription())
                .quantity(data.getQuantity())
                .serialNumber(data.getSerialNumber())
                .batch(data.getBatch())
                .inventory(inventory)
                .createdBy(user)
                .category(new ArrayList<>(categories))
                .createDate(LocalDateTime.now())
                .active(data.isActive())
                .build();

        return productMapper.toDto(productService.saveProduct(productEntity));
    }

    public DtoProduct updateProduct(DtoUpdateProduct product){
        ProductEntity productEntity = productService.getProductById(product.getId());
        InventoryEntity inventory = inventoryService.getInventoryById(product.getInventory());

        if(productService.existsByNameAndIdNot(product.getName(), product.getId())){
            throw new ExceptionProductNameAlreadyInUse();
        }

        if(product.getSerialNumber() != null){
            if(productService.existsBySerialNumberAndIdNot(product.getSerialNumber(), product.getId())){
                throw new ExceptionProductSerialNumberAlreadyInUse();
            }
        }

        List<CategoryEntity> categories = new ArrayList<>();

        if(product.getCategories() != null){
            categories = categoryService.getCategoriesByIdsOrThrow(product.getCategories());
        }

        productEntity.setBatch(product.getBatch());
        productEntity.setActive(product.isActive());
        productEntity.setDescription(product.getDescription());
        productEntity.setName(product.getName());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setSerialNumber(product.getSerialNumber());
        productEntity.setCategory(categories);
        productEntity.setInventory(inventory);

        productService.saveProduct(productEntity);

        return productMapper.toDto(productEntity);
    }
}
