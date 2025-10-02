package com.vasscommerce.vasscommerce.config;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import com.vasscommerce.vasscommerce.dto.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorResponse.FieldError> fieldErrors = new ArrayList<>();

        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.add(new ErrorResponse.FieldError(fe.getField(), fe.getDefaultMessage()));
        }

        ErrorResponse error = new ErrorResponse("Invalid input", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralErrors(Exception ex) {
        List<ErrorResponse.FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new ErrorResponse.FieldError("system", "Ocorreu um erro inesperado"));

        ErrorResponse error = new ErrorResponse("Internal server error", fieldErrors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}