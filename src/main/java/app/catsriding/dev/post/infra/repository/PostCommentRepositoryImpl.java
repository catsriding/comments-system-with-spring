package app.catsriding.dev.post.infra.repository;

import app.catsriding.dev.post.application.port.PostCommentRepository;
import app.catsriding.dev.post.domain.model.PostComment;
import app.catsriding.dev.post.domain.value.PostCommentItem;
import app.catsriding.dev.post.domain.value.PostId;
import app.catsriding.dev.post.infra.cond.PostCommentPageableCond;
import app.catsriding.dev.post.infra.entity.PostCommentEntity;
import app.catsriding.dev.post.infra.result.PostCommentItemResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PostCommentRepositoryImpl implements PostCommentRepository {

    private final PostCommentJpaRepository jpaRepository;

    @Override
    public PostComment save(PostComment postComment) {
        return jpaRepository.save(PostCommentEntity.from(postComment)).toModel();
    }

    @Override
    public List<PostCommentItem> fetchBy(PostCommentPageableCond cond) {
        List<PostCommentItemResult> results = jpaRepository.fetchBy(cond);
        return PostCommentItem.from(results);
    }

    @Override
    @Transactional(readOnly = true)
    public long countTotal(PostId postId) {
        return jpaRepository.countTotal(postId);
    }

}
