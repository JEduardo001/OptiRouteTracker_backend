package com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoResetPassword {
    @NotNull
    Long id;
    @NotEmpty
    String currentPassword;
    @NotEmpty
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_\\-]).{8,35}$", // 1 upperCase, 1 lower case, 1 number 1 symbol
            message = "Password must contain upper, lower, number and special character"
    )
    String newPassword;
    @NotEmpty
    String confirmPassword;

}
