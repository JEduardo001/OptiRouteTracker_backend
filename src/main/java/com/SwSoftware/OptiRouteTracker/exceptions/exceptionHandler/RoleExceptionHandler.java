package com.SwSoftware.OptiRouteTracker.exceptions.exceptionHandler;

import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.exceptions.role.ExceptionRoleNameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.role.ExceptionRoleNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoleExceptionHandler {


    @ExceptionHandler(ExceptionRoleNotFound.class)
    public ResponseEntity<DtoResponseApi> ExceptionRoleNotFound(ExceptionRoleNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Role not found")
                        .build());
    }

    @ExceptionHandler(ExceptionRoleNameAlreadyInUse.class)
    public ResponseEntity<DtoResponseApi> ExceptionRoleNameAlreadyInUse(ExceptionRoleNameAlreadyInUse ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Role name already in use")
                        .build());
    }
}
