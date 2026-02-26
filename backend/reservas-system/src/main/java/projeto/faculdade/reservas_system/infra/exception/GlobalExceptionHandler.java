package projeto.faculdade.reservas_system.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import projeto.faculdade.reservas_system.application.user.exception.EmailAlreadyUsedException;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exc) {
        List<FieldError> erros = exc.getFieldErrors();

        return ResponseEntity.badRequest().body(
                erros.stream().map(FieldExceptionResponse::new).toList());
    }

    @ExceptionHandler({EmailAlreadyUsedException.class})
    public ResponseEntity<?> validationExceptionHandler(EmailAlreadyUsedException exc) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 400, exc.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}

record DefaultExceptionResponse(LocalDateTime timestamp,
                                Integer status,
                                String error){}
record FieldExceptionResponse(String field, String message) {
    public FieldExceptionResponse(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}