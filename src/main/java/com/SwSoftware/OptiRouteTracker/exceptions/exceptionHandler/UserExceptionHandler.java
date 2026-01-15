package com.SwSoftware.OptiRouteTracker.exceptions.exceptionHandler;

import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserNotFound;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionPasswordsDoNotMatch;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserEmailAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserUsernameAlreadyInUse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(ExceptionUserUsernameAlreadyInUse.class)
    public ResponseEntity<DtoResponseApi> ExceptionUserUsernameAlreadyInUse(ExceptionUserUsernameAlreadyInUse ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Username already in use")
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

    @ExceptionHandler(ExceptionUserNotFound.class)
    public ResponseEntity<DtoResponseApi> ExceptionUserNotFound(ExceptionUserNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("User not found")
                        .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<DtoResponseApi> BadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Incorrect password")
                        .build());
    }
}
