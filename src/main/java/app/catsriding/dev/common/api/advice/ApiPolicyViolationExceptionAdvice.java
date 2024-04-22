package app.catsriding.dev.common.api.advice;

import app.catsriding.dev.common.api.enums.RestApiStatus;
import app.catsriding.dev.common.api.resopnse.RestApiResponse;
import app.catsriding.dev.post.application.exception.PostCommentAdditionException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiPolicyViolationExceptionAdvice {

    @ExceptionHandler(PostCommentAdditionException.class)
    protected ResponseEntity<?> advice(HttpServletRequest request, PostCommentAdditionException e) {
        String api = request.getMethod() + " " + request.getRequestURI();
        log.info("advice: [{}] {} ", api, e.getMessage());
        return ResponseEntity
                .ok()
                .body(RestApiResponse.write(RestApiStatus.BAD_REQUEST, e.getMessage()));
    }

}
