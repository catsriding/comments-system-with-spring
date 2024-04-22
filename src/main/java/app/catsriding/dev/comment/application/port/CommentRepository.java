package app.catsriding.dev.comment.application.port;

import app.catsriding.dev.comment.domain.model.Comment;

public interface CommentRepository {

    Comment save(Comment comment);
}
