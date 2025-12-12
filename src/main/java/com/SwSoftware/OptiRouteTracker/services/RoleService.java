package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.resource.ExceptionRoleNotFound;
import com.SwSoftware.OptiRouteTracker.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepositor){
        this.roleRepository = roleRepositor;
    }

    public RoleEntity getRoleById(Long idRole){
        return roleRepository.findById(idRole).orElseThrow(ExceptionRoleNotFound::new);

    }


}
