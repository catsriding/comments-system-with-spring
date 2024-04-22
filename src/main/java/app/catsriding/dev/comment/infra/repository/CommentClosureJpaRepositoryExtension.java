package app.catsriding.dev.comment.infra.repository;

import app.catsriding.dev.comment.domain.value.CommentId;
import app.catsriding.dev.comment.domain.value.ParentCommentId;
import app.catsriding.dev.comment.infra.entity.CommentClosureEntity;
import app.catsriding.dev.comment.infra.result.NestedCommentStatResult;
import app.catsriding.dev.comment.domain.value.NestedCommentItem;
import java.util.List;

public interface CommentClosureJpaRepositoryExtension {

    List<CommentClosureEntity> fetchAllAncestorsBy(ParentCommentId parentCommentId);

    List<NestedCommentStatResult> fetchNestedCommentStats(List<Long> commentIds);

    List<NestedCommentItem> fetchNestedCommentsBy(CommentId commentId);

    long countNestedCommentsBy(CommentId commentId);

}
