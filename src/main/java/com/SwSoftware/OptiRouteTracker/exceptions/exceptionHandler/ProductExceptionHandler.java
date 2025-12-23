package com.SwSoftware.OptiRouteTracker.exceptions.exceptionHandler;

import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductNameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.product.ExceptionProductSerialNumberAlreadyInUse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ExceptionProductNotFound.class)
    public ResponseEntity ExceptionProductNotFound(ExceptionProductNotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Product not found")
                        .build());
    }

    @ExceptionHandler(ExceptionProductSerialNumberAlreadyInUse.class)
    public ResponseEntity ExceptionProductSerialNumberAlreadyInUse(ExceptionProductSerialNumberAlreadyInUse ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Product serial number, already in use")
                        .build());
    }

    @ExceptionHandler(ExceptionProductNameAlreadyInUse.class)
    public ResponseEntity ExceptionProductNameAlreadyInUse(ExceptionProductNameAlreadyInUse ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Product name already in use")
                        .build());
    }
}
