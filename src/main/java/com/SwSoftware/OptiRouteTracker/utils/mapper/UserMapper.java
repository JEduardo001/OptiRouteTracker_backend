package com.SwSoftware.OptiRouteTracker.utils.mapper;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoUser;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    DtoUser toDto(UserEntity user);
}
