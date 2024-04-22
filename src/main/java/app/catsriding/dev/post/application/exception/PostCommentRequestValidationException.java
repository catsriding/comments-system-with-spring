package app.catsriding.dev.post.application.exception;

public class PostCommentRequestValidationException extends RuntimeException {

    public PostCommentRequestValidationException() {
    }

    public PostCommentRequestValidationException(String message) {
        super(message);
    }

    public PostCommentRequestValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostCommentRequestValidationException(Throwable cause) {
        super(cause);
    }
}
