package com.SwSoftware.OptiRouteTracker.controllers.auth;

import com.SwSoftware.OptiRouteTracker.dtos.dtosAuth.DtoLogin;
import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoRole;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoCreateUser;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoUserLogIn;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApiLogIn;
import com.SwSoftware.OptiRouteTracker.security.JwtSecurity.JwtService;
import com.SwSoftware.OptiRouteTracker.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.baseApi + "/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserService userService,AuthenticationManager authenticationManager, JwtService jwtService){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<DtoResponseApi> createUser(@Valid @RequestBody DtoCreateUser data){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("User registred")
                .data(userService.createUser(data))
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
                .user(userService.getUserToLogin(data.getUsername()))
                .build()
        );
    }

}
