package com.SwSoftware.OptiRouteTracker.controllers.auth;

import com.SwSoftware.OptiRouteTracker.dtos.dtosAuth.DtoLogin;
import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoCreateUser;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoResetPassword;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApiLogIn;
import com.SwSoftware.OptiRouteTracker.interfaces.IUserService;
import com.SwSoftware.OptiRouteTracker.security.JwtSecurity.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.baseApi + "/auth")
@AllArgsConstructor
public class AuthController {

    private final IUserService iUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<DtoResponseApi> createUser(@Valid @RequestBody DtoCreateUser data){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("User registred")
                .data(iUserService.createUser(data))
                .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<DtoResponseApiLogIn> login(@Valid @RequestBody DtoLogin data){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(),data.getPassword()));
        String token = jwtService.createToken(authentication.getName());

        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApiLogIn.builder()
                .status(HttpStatus.OK.value())
                .message("logged")
                .token(token)
                .user(iUserService.getUserToLogin(data.getUsername()))
                .build()
        );
    }

    @PostMapping()
    public ResponseEntity<DtoResponseApi> resetPassword(@Valid @RequestBody DtoResetPassword request){
        iUserService.resetPassword(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(DtoResponseApi.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Password reset")
                .build()
        );
    }
}
