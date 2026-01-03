package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.category.DtoCategory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoCreateRole;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoRole;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoUpdateRole;
import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.category.ExceptionCategoryNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.role.ExceptionRoleNameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.role.ExceptionRoleNotFound;
import com.SwSoftware.OptiRouteTracker.repositories.RoleRepository;
import com.SwSoftware.OptiRouteTracker.utils.mapper.RoleMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepositor, RoleMapper roleMapper){
        this.roleRepository = roleRepositor;
        this.roleMapper = roleMapper;
    }

    public RoleEntity getRoleById(Long idRole){
        return roleRepository.findById(idRole).orElseThrow(ExceptionRoleNotFound::new);
    }

    public boolean existRoleByName(String name){
        return roleRepository.existsByName(name);
    }

    public boolean existRoleById(Long idRole){
        return roleRepository.existsById(idRole);
    }

    public Set<RoleEntity> getRolesByIdsOrThrow(Set<Long> ids) {
        if(ids != null){
            Set<RoleEntity> result = roleRepository.findByIdIn(ids);
            if (result.size() != ids.size()) {
                throw new ExceptionRoleNotFound();
            }
            return result;
        }

        return new LinkedHashSet<>();
    }

    public DtoPageableResponse<DtoRole> getAllRoles(Integer page, Integer size){
        Page<RoleEntity> roles = roleRepository.findAll(PageRequest.of(page,size));
        List<DtoRole> dtoRoles = roles.getContent().stream().map(roleMapper::toDto).collect(Collectors.toList());
        return new DtoPageableResponse<DtoRole>(
                roles.getTotalElements(),
                roles.getTotalPages(),
                dtoRoles
        );
    }

    public DtoRole getRole(Long idRole){
        return roleMapper.toDto(getRoleById(idRole));
    }

    public DtoRole createRole(DtoCreateRole request){
        if(existRoleByName(request.getName())){
            throw new ExceptionRoleNameAlreadyInUse();
        }

        RoleEntity role = RoleEntity.builder()
                .active(request.isActive())
                .name(request.getName())
                .build();

        return roleMapper.toDto(roleRepository.save(role));
    }

    public DtoRole updateRole(DtoUpdateRole request){
        RoleEntity role = getRoleById(request.getId());
        if(roleRepository.existsByNameAndIdNot(request.getName(), request.getId())){
            throw new ExceptionRoleNameAlreadyInUse();
        }

        role.setActive(request.isActive());
        role.setName(request.getName());
        return roleMapper.toDto(roleRepository.save(role));
    }
}
