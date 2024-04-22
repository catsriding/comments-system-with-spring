package app.catsriding.dev.comment.infra.repository;

import app.catsriding.dev.comment.application.port.CommentClosureRepository;
import app.catsriding.dev.comment.domain.model.CommentClosure;
import app.catsriding.dev.comment.domain.value.CommentId;
import app.catsriding.dev.comment.domain.value.NestedCommentItem;
import app.catsriding.dev.comment.domain.value.ParentCommentId;
import app.catsriding.dev.comment.infra.entity.CommentClosureEntity;
import app.catsriding.dev.comment.infra.result.NestedCommentStatResult;
import app.catsriding.dev.common.application.helper.StreamHelper;
import app.catsriding.dev.post.domain.value.CommentStat;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CommentClosureRepositoryImpl implements CommentClosureRepository {

    private final CommentClosureJpaRepository jpaRepository;

    @Override
    public CommentClosure save(CommentClosure commentClosure) {
        return jpaRepository.save(CommentClosureEntity.from(commentClosure)).toModel();
    }

    @Override
    public List<CommentClosure> fetchAncestors(ParentCommentId parentCommentId) {
        return jpaRepository.fetchAllAncestorsBy(parentCommentId)
                .stream()
                .map(CommentClosureEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentStat> retrieveCommentStatsBy(List<Long> commentIds) {
        List<NestedCommentStatResult> results = jpaRepository.fetchNestedCommentStats(commentIds);
        return StreamHelper.transform(results, CommentStat::from);
    }

    @Override
    public List<NestedCommentItem> retrieveNestedComments(CommentId commentId) {
        return jpaRepository.fetchNestedCommentsBy(commentId);
    }

    @Override
    public long countTotal(CommentId commentId) {
        return jpaRepository.countNestedCommentsBy(commentId);
    }

}
