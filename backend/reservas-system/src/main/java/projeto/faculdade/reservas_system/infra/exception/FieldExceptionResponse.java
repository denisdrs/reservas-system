package projeto.faculdade.reservas_system.infra.exception;

import org.springframework.validation.FieldError;

record FieldExceptionResponse(String field, String message) {
    public FieldExceptionResponse(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}