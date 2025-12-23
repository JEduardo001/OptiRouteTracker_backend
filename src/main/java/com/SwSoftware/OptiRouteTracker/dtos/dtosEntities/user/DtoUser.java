package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private boolean active;
    private LocalDate birthday;
    private Set<DtoRole> roles;
}
