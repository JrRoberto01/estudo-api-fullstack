package com.vasscommerce.vasscommerce.dto;

import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorResponse {
    private String error;
    private List<FieldError> fields;

    public ErrorResponse(String error, List<FieldError> fields) {
        this.error = error;
        this.fields = fields;
    }

    public String getError() {
        return error;
    }

    public List<FieldError> getFields() {
        return fields;
    }

    public static class FieldError{
        private String name;
        private String message;

        public FieldError(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }
    }
}
