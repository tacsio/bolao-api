package br.com.deveficiente.bolaoapi.shared.api;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String name;
            if (error instanceof FieldError fieldObject) {
                name = fieldObject.getField();
            } else {
                name = error.getObjectName().replaceAll("Request", "");
            }

            String errorMessage = error.getDefaultMessage();
            errors.put(name, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<ErrorResponse> handleValidationExceptions(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(violation -> new ErrorResponse(violation.getMessage()))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleCustomValidationException(IllegalArgumentException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
