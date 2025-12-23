package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.entities.CategoryEntity;
import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.category.ExceptionCategoryNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.resource.ExceptionRoleNotFound;
import com.SwSoftware.OptiRouteTracker.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepositor){
        this.roleRepository = roleRepositor;
    }

    public RoleEntity getRoleById(Long idRole){
        return roleRepository.findById(idRole).orElseThrow(ExceptionRoleNotFound::new);
    }


    public Set<RoleEntity> getRolesByIdsOrThrow(Set<Long> ids) {
        if(ids != null){
            Set<RoleEntity> result = roleRepository.findByIdIn(ids);
            if (result.size() != ids.size()) {
                throw new ExceptionCategoryNotFound();
            }
            return result;
        }

        return new LinkedHashSet<>();
    }


}
