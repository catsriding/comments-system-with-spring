package app.catsriding.dev.post.infra.repository;

import app.catsriding.dev.post.application.exception.PostCommentAdditionException;
import app.catsriding.dev.post.application.port.PostRepository;
import app.catsriding.dev.post.domain.model.Post;
import app.catsriding.dev.post.domain.value.PostId;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository jpaRepository;

    @Override
    public Post fetchBy(PostId postId) {
        return jpaRepository.fetchBy(postId)
                .orElseThrow(exception(postId))
                .toModel();
    }

    private static Supplier<RuntimeException> exception(PostId postId) {
        return () -> {
            log.info("fetchBy: Does not found post by postId={}", postId.getId());
            return new PostCommentAdditionException(
                    "Unable to retrieve the post for adding a comment. "
                    + "Please verify that the post exists and try again.");
        };
    }

}
