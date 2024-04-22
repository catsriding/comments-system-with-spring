package app.catsriding.dev.comment.infra.repository;

import app.catsriding.dev.comment.application.port.CommentRepository;
import app.catsriding.dev.comment.domain.model.Comment;
import app.catsriding.dev.comment.infra.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository jpaRepository;

    @Override
    public Comment save(Comment comment) {
        return jpaRepository.save(CommentEntity.from(comment)).toModel();
    }
}
