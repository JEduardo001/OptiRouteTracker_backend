package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public record DtoUserLogIn(
        Long id,
        String username,
        String email,
        boolean active,
        List<DtoRole> roles
) {

}
