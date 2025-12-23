package com.SwSoftware.OptiRouteTracker.repositories;

import com.SwSoftware.OptiRouteTracker.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findByInventoryId(Long id, Pageable page);
    boolean existsByNameAndIdNot(String name, Long id);
    boolean existsBySerialNumberAndIdNot(String serialNumber, Long id);
    @Modifying
    @Query("DELETE FROM ProductEntity p WHERE p.id = :idProduct AND p.inventory.id = :inventoryId")
    void deleteByIdAndInventoryId(Long idProduct, Long inventoryId);


}
