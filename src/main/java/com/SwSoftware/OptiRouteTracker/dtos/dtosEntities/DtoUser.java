package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities;

import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    private LocalDate birthday;
    private List<DtoRole> roles;
}
