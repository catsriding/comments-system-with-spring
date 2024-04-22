package app.catsriding.dev.comment.application.port;

import app.catsriding.dev.comment.domain.model.CommentClosure;
import app.catsriding.dev.comment.domain.value.CommentId;
import app.catsriding.dev.comment.domain.value.ParentCommentId;
import app.catsriding.dev.post.domain.value.CommentStat;
import app.catsriding.dev.comment.domain.value.NestedCommentItem;
import java.util.List;

public interface CommentClosureRepository {

    CommentClosure save(CommentClosure commentClosure);

    List<CommentClosure> fetchAncestors(ParentCommentId parentCommentId);

    List<CommentStat> retrieveCommentStatsBy(List<Long> commentIds);

    List<NestedCommentItem> retrieveNestedComments(CommentId commentId);

    long countTotal(CommentId commentId);
}
