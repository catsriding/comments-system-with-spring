package app.catsriding.dev.common.api.advice;

import static app.catsriding.dev.common.api.enums.RestApiStatus.BAD_REQUEST;

import app.catsriding.dev.common.api.resopnse.RestApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RequestValidationExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> advice(HttpServletRequest request, MethodArgumentNotValidException ex) {
        String api = request.getMethod() + " " + request.getRequestURI();

        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        this::getErrorMessageOrDefault,
                        this::mergeErrorMessages));

        log.info("advice: [{}] - {}", api, errors);

        return ResponseEntity
                .badRequest()
                .body(RestApiResponse.write(BAD_REQUEST, errors));
    }

    private String getErrorMessageOrDefault(FieldError fieldError) {
        String defaultMessage = String.format(
                "Validation failed for field %s. "
                + "The provided value is invalid. "
                + "Please check your data and try again.",
                fieldError.getField());
        return Optional.ofNullable(fieldError.getDefaultMessage()).orElse(defaultMessage);
    }

    private String mergeErrorMessages(String prev, String next) {
        return String.join(", ", prev, next);
    }

}
