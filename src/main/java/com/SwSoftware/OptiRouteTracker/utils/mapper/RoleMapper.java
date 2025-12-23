package com.SwSoftware.OptiRouteTracker.utils.mapper;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoRole;
import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    DtoRole toDto(RoleEntity role);
}
