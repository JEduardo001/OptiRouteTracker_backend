package com.SwSoftware.OptiRouteTracker.repositories;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithProducts;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithoutProducts;
import com.SwSoftware.OptiRouteTracker.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    boolean existsByName(String name);
    @Query("""
         select new com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithoutProducts(
             i.id, i.name, i.description, i.createDate, i.location, i.quantity
         )
         from InventoryEntity i
         where i.id = :id
        """)
    Optional<DtoInventoryWithoutProducts> findInventoryById(@Param("id") Long id);
    @Modifying
    @Query("DELETE FROM ProductEntity p WHERE p.inventory.id = :inventoryId")
    void deleteByInventoryId(@Param("inventoryId") Long inventoryId);
}
