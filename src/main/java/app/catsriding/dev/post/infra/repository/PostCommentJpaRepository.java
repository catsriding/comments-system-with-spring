package app.catsriding.dev.post.infra.repository;

import app.catsriding.dev.post.infra.entity.PostCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentJpaRepository extends
        JpaRepository<PostCommentEntity, Long>,
        PostCommentJpaRepositoryExtension {

}