package com.SwSoftware.OptiRouteTracker.controllers.role;

import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoCreateRole;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoUpdateRole;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.interfaces.IRoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.baseApi + "/role")
@AllArgsConstructor
public class RoleController {

    private final IRoleService iRoleService;

    @GetMapping()
    public ResponseEntity<DtoResponseApi> getAllRoles(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Roles obtained")
                .data(iRoleService.getAllRoles(page,size))
                .build()
        );
    }

    @GetMapping("/{idRole}")
    public ResponseEntity<DtoResponseApi> getRole(@PathVariable Long idRole){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Role obtained")
                .data(iRoleService.getRole(idRole))
                .build()
        );
    }

    @PostMapping
    public ResponseEntity<DtoResponseApi> createRole(@Valid @RequestBody DtoCreateRole request){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("Role created")
                .data(iRoleService.createRole(request))
                .build()
        );
    }

    @PutMapping
    public ResponseEntity<DtoResponseApi> updateRole(@Valid @RequestBody DtoUpdateRole request){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Updated Role")
                .data(iRoleService.updateRole(request))
                .build()
        );
    }
}
