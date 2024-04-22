package app.catsriding.dev.comment.infra.repository;

import app.catsriding.dev.comment.infra.entity.CommentClosureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentClosureJpaRepository extends
        JpaRepository<CommentClosureEntity, Long>,
        CommentClosureJpaRepositoryExtension {

}