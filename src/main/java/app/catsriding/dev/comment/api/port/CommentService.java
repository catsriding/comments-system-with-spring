package app.catsriding.dev.comment.api.port;

import app.catsriding.dev.comment.domain.model.Comment;
import app.catsriding.dev.comment.domain.value.CommentCreate;
import app.catsriding.dev.comment.domain.value.CommentId;
import app.catsriding.dev.comment.api.response.NestedCommentsResponse;
import java.util.List;
import java.util.Map;

public interface CommentService {

    Comment createComment(CommentCreate commentCreate);

    Map<Long, Boolean> hasNestedComments(List<Long> commentIds);

    NestedCommentsResponse retrieveNestedComments(CommentId commentId);

}
