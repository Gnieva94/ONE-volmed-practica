package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DTOValidationError::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    private record DTOValidationError(String campo, String error){
        public DTOValidationError(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }

}
