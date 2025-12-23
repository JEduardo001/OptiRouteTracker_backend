package com.SwSoftware.OptiRouteTracker.repositories;

import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    List<CategoryEntity> findByIdIn(List<Long> ids);
    boolean existsByName(String name);

}
