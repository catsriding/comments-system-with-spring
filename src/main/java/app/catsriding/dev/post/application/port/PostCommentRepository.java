package app.catsriding.dev.post.application.port;

import app.catsriding.dev.post.domain.model.PostComment;
import app.catsriding.dev.post.domain.value.PostCommentItem;
import app.catsriding.dev.post.domain.value.PostId;
import app.catsriding.dev.post.infra.cond.PostCommentPageableCond;
import java.util.List;

public interface PostCommentRepository {

    PostComment save(PostComment postComment);

    List<PostCommentItem> fetchBy(PostCommentPageableCond cond);

    long countTotal(PostId postId);

}
