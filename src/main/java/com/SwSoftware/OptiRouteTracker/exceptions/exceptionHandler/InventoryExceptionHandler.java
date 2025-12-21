package com.SwSoftware.OptiRouteTracker.exceptions.exceptionHandler;

import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.exceptions.inventory.ExceptionInventoryNameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.inventory.ExceptionInventoryNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.resource.ExceptionRoleNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InventoryExceptionHandler {


    @ExceptionHandler(ExceptionInventoryNameAlreadyInUse.class)
    public ResponseEntity<DtoResponseApi> ExceptionInventoryNameAlreadyInUse(ExceptionInventoryNameAlreadyInUse ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Name inventory already in use")
                        .build());
    }

    @ExceptionHandler(ExceptionInventoryNotFound.class)
    public ResponseEntity<DtoResponseApi> ExceptionInventoryNotFound(ExceptionInventoryNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Inventory not found")
                        .build());
    }
}
