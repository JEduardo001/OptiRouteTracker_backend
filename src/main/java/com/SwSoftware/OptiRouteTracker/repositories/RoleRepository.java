package com.SwSoftware.OptiRouteTracker.repositories;

import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    Set<RoleEntity> findByIdIn(Set<Long> idsRoles);
}
