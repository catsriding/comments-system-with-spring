package app.catsriding.dev.post.application.exception;

public class PostCommentAdditionException extends RuntimeException {

    public PostCommentAdditionException() {
    }

    public PostCommentAdditionException(String message) {
        super(message);
    }

    public PostCommentAdditionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostCommentAdditionException(Throwable cause) {
        super(cause);
    }
}
