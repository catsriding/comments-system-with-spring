package app.catsriding.dev.post.infra.repository;

import app.catsriding.dev.post.domain.value.PostId;
import app.catsriding.dev.post.infra.entity.PostEntity;
import java.util.Optional;

public interface PostJpaRepositoryExtension {

    Optional<PostEntity> fetchBy(PostId postId);

}
