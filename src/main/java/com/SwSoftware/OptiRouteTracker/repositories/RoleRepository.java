package com.SwSoftware.OptiRouteTracker.repositories;

import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    List<RoleEntity> findByIdIn(List<Long> idsRoles);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
