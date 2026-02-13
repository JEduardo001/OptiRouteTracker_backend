package com.SwSoftware.OptiRouteTracker.interfaces;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoCreateInventory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithProducts;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithoutProducts;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoUpdateInventory;
import com.SwSoftware.OptiRouteTracker.entities.InventoryEntity;
import org.springframework.transaction.annotation.Transactional;

public interface IInventoryService {
    InventoryEntity getInventoryById(Long idInventory);
    @Transactional
    DtoInventoryWithoutProducts createInventory(DtoCreateInventory data);
    DtoInventoryWithProducts getAllDataInventory(Long idInventory);
    DtoPageableResponse<DtoInventoryWithoutProducts> getAllInventories(Integer page, Integer size);
    @Transactional
    void removeInventoryProduct(Long idProduct, Long idInventory);
    @Transactional
    DtoInventoryWithoutProducts updateInventory(DtoUpdateInventory data);
    @Transactional
    void removeInventory(Long idInventory);
}
