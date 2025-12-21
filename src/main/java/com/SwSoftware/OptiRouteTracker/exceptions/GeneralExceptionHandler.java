package com.SwSoftware.OptiRouteTracker.exceptions;

import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.exceptions.resource.ExceptionUserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DtoResponseApi> MissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DtoResponseApi.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Bad params")
                        .build());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> handleInternalAuth(InternalAuthenticationServiceException ex) throws Exception{

        Throwable original = ex.getCause();
        if (original instanceof ExceptionUserNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(DtoResponseApi.builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .message("User not found")
                            .build());
        }

        throw new Exception();
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
