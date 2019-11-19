package @packageName@.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnknownResourceException.class)
    public ResponseEntity<?> handleUnknownResource(UnknownResourceException exception) {
        return createResponseEntity(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleGenericException(Exception exception) {
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error has occurred");
    }

    private ResponseEntity<?> createResponseEntity(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(message);
    }
}
