package br.com.raquel.datahubprototype.infra.Interceptor;

import br.com.raquel.datahubprototype.Exceptions.UnauthorizedException;
import br.com.raquel.datahubprototype.utils.DefaultRestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    protected DefaultRestResponse handleUnauthorizedError(
            RuntimeException exception) {
        return DefaultRestResponse.builder().message(exception.getLocalizedMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected DefaultRestResponse handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return DefaultRestResponse.builder().message(errors.toString()).build();
    }
}
