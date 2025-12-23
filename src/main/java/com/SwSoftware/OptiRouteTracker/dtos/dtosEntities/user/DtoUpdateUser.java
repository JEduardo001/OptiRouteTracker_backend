package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUpdateUser {
    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String username;
    private String email;
    @NotBlank
    private String name;
    @NotNull
    private boolean active;
    private String lastname;
    @NotNull
    private LocalDate birthday;
    @NotNull
    private Set<Long> idRoles;
}

