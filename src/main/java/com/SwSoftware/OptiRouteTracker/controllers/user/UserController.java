package com.SwSoftware.OptiRouteTracker.controllers.user;

import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoCreateUser;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoUpdateUser;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.baseApi + "/user")
@AllArgsConstructor
public class UserController {

    private final IUserService iUserService;

    @GetMapping()
    public ResponseEntity<DtoResponseApi> getAllUsers(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Users obtained")
                .data(iUserService.getAllUsers(page,size))
                .build()
        );
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> getUser(@PathVariable Long idUser){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("User obtained")
                .data(iUserService.getUser(idUser))
                .build()
        );
    }

    @PostMapping()
    public ResponseEntity<DtoResponseApi> createUser(@Valid @RequestBody DtoCreateUser request){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("Created user")
                .data(iUserService.createUser(request))
                .build()
        );
    }

    @PutMapping()
    public ResponseEntity<DtoResponseApi> updateUser(@Valid @RequestBody DtoUpdateUser request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Updated user")
                .data(iUserService.updateUser(request))
                .build()
        );
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<DtoResponseApi> disableUser(@PathVariable Long idUser){
        iUserService.disableUser(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Disabled user")
                .build()
        );
    }
}
