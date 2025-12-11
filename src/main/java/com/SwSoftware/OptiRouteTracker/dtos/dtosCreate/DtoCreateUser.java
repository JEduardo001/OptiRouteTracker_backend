package com.SwSoftware.OptiRouteTracker.dtos.dtosCreate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoCreateUser {
    @NotBlank()
    private String username;
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    @Size(min = 8, max = 35)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_\\-]).{8,35}$", // 1 upperCase, 1 lower case, 1 number 1 symbol
            message = "Password must contain upper, lower, number and special character"

            )
    private String password;
    @Size(min = 8, max = 35)
    private String passwordRepeat;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private List<Long> rolesId;
}
