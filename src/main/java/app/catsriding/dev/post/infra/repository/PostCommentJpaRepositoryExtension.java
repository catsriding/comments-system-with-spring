package app.catsriding.dev.post.infra.repository;

import app.catsriding.dev.post.domain.value.PostId;
import app.catsriding.dev.post.infra.cond.PostCommentPageableCond;
import app.catsriding.dev.post.infra.result.PostCommentItemResult;
import java.util.List;

public interface PostCommentJpaRepositoryExtension {

    List<PostCommentItemResult> fetchBy(PostCommentPageableCond cond);

    long countTotal(PostId postId);

}
