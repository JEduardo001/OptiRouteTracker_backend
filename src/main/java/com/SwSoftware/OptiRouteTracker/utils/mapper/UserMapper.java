package com.SwSoftware.OptiRouteTracker.utils.mapper;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoCreateUser;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoUser;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoUserLogIn;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    DtoUser toDto(UserEntity user);
    UserEntity toEntity(DtoCreateUser user);
    DtoUserLogIn toUserDtoLogin(UserEntity username);
}
