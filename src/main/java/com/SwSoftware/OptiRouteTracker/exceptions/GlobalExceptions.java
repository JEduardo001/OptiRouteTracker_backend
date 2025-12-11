package com.SwSoftware.OptiRouteTracker.exceptions;

import com.SwSoftware.OptiRouteTracker.constants.ConstantsExceptions;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.exceptions.customExceptions.exceptionsCreateResources.ExceptionPasswordsDoNotMatch;
import com.SwSoftware.OptiRouteTracker.exceptions.customExceptions.exceptionsCreateResources.ExceptionUserEmailAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.customExceptions.exceptionsCreateResources.ExceptionUserUsernameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.customExceptions.exceptionsNotFoundResourcers.ExceptionRoleNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DtoResponseApi> handleValidation(MethodArgumentNotValidException ex) {

        String mensaje = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(mensaje)
                        .build());
    }

    @ExceptionHandler(ExceptionUserUsernameAlreadyInUse.class)
    public ResponseEntity<DtoResponseApi> ExceptionUserUsernameAlreadyInUse(ExceptionUserUsernameAlreadyInUse ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Username already in use")
                        .build());
    }

    @ExceptionHandler(ExceptionRoleNotFound.class)
    public ResponseEntity<DtoResponseApi> ExceptionRoleNotFound(ExceptionRoleNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Role not found")
                        .build());
    }

    @ExceptionHandler(ExceptionPasswordsDoNotMatch.class)
    public ResponseEntity<DtoResponseApi> ExceptionPasswordsDoNotMatch(ExceptionPasswordsDoNotMatch ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Passwords do not match")
                        .build());
    }

    @ExceptionHandler(ExceptionUserEmailAlreadyInUse.class)
    public ResponseEntity<DtoResponseApi> ExceptionUserEmailAlreadyInUse(ExceptionUserEmailAlreadyInUse ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Email already in use")
                        .build());
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DtoResponseApi> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("invalid data")
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DtoResponseApi> genericException(Exception ex) {
        ex.printStackTrace();
        //ConstantsExceptions.genericConstantException
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .build());
    }
}
