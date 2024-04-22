package app.catsriding.dev.comment.infra.repository;

import app.catsriding.dev.comment.infra.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long>, CommentJpaRepositoryExtension {

}