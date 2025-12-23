package com.SwSoftware.OptiRouteTracker.exceptions.exceptionHandler;

import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.exceptions.category.ExceptionCategoryNameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.category.ExceptionCategoryNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.inventory.ExceptionInventoryNameAlreadyInUse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class CategoryExceptionHandler {


    @ExceptionHandler(ExceptionCategoryNotFound.class)
    public ResponseEntity<DtoResponseApi> ExceptionCategoryNotFound(ExceptionCategoryNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Category not found")
                        .build());
    }

    @ExceptionHandler(ExceptionCategoryNameAlreadyInUse.class)
    public ResponseEntity<DtoResponseApi> ExceptionCategoryNameAlreadyInUse(ExceptionCategoryNameAlreadyInUse ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Name Category already in use")
                        .build());
    }


}
