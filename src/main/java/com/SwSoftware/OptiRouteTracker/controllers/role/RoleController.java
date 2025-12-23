package com.SwSoftware.OptiRouteTracker.controllers.role;

import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoCreateRole;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoUpdateRole;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.baseApi + "/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<DtoResponseApi> getAllRoles(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Roles obtained")
                .data(roleService.getAllRoles(page,size))
                .build()
        );
    }

    @GetMapping("/{idRole}")
    public ResponseEntity<DtoResponseApi> getRole(@PathVariable Long idRole){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Role obtained")
                .data(roleService.getRole(idRole))
                .build()
        );
    }

    @PostMapping
    public ResponseEntity<DtoResponseApi> createRole(@Valid @RequestBody DtoCreateRole request){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("Role created")
                .data(roleService.createRole(request))
                .build()
        );
    }

    @PutMapping
    public ResponseEntity<DtoResponseApi> updateRole(@Valid @RequestBody DtoUpdateRole request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Updated Role")
                .data(roleService.updateRole(request))
                .build()
        );
    }
}
