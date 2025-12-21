package com.SwSoftware.OptiRouteTracker.utils.mapper;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.DtoUser;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    @BeanMapping(builder = @Builder(disableBuilder = true))
    DtoUser toDto(UserEntity user);
}
