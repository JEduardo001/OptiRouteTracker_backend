package com.SwSoftware.OptiRouteTracker.interfaces;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoCreateRole;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoRole;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoUpdateRole;
import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoleService {
    List<RoleEntity> getRolesByIdsOrThrow(List<DtoRole> roles);
    DtoPageableResponse<DtoRole> getAllRoles(Integer page, Integer size);
    DtoRole getRole(Long idRole);
    @Transactional
    DtoRole createRole(DtoCreateRole request);
    @Transactional
    DtoRole updateRole(DtoUpdateRole request);
}
