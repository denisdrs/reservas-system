package projeto.faculdade.reservas_system.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import projeto.faculdade.reservas_system.application.order.exception.StockException;
import projeto.faculdade.reservas_system.application.product.exception.ProductNotFoundException;
import projeto.faculdade.reservas_system.application.user.exception.EmailAlreadyUsedException;
import projeto.faculdade.reservas_system.application.user.exception.UserNotFoundException;

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

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> badCredentialsExceptionHandler(BadCredentialsException exc) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 401, exc.getMessage());
        return ResponseEntity.status(401).body(response);
    }
    @ExceptionHandler({UserNotFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<?> notFoundExceptionHandler(RuntimeException exc) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 404, exc.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler({StockException.class,IllegalArgumentException.class})
    public ResponseEntity<?> unprocessableEntityHandler(RuntimeException exc) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 422, exc.getMessage());
        return ResponseEntity.status(422).body(response);
    }
}