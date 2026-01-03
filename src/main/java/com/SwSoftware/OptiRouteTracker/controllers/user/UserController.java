package com.SwSoftware.OptiRouteTracker.controllers.user;

import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoCreateUser;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoUpdateUser;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.baseApi + "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<DtoResponseApi> getAllUsers(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Users obtained")
                .data(userService.getAllUsers(page,size))
                .build()
        );
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> getUser(@PathVariable Long idUser){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("User obtained")
                .data(userService.getUser(idUser))
                .build()
        );
    }

    @PostMapping()
    public ResponseEntity<DtoResponseApi> createUser(@Valid @RequestBody DtoCreateUser request){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("Created user")
                .data(userService.createUser(request))
                .build()
        );
    }

    @PutMapping()
    public ResponseEntity<DtoResponseApi> updateUser(@Valid @RequestBody DtoUpdateUser request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Updated user")
                .data(userService.updateUser(request))
                .build()
        );
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> disableUser(@PathVariable Long idUser){
        userService.disableUser(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Disabled user")
                .build()
        );
    }
}
