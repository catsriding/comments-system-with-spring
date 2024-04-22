package app.catsriding.dev.comment.infra.repository;

import static app.catsriding.dev.comment.infra.entity.QCommentClosureEntity.commentClosureEntity;

import app.catsriding.dev.comment.domain.value.CommentId;
import app.catsriding.dev.comment.domain.value.NestedCommentItem;
import app.catsriding.dev.comment.domain.value.ParentCommentId;
import app.catsriding.dev.comment.infra.entity.CommentClosureEntity;
import app.catsriding.dev.comment.infra.entity.QCommentClosureEntity;
import app.catsriding.dev.comment.infra.entity.QCommentEntity;
import app.catsriding.dev.comment.infra.result.NestedCommentStatResult;
import app.catsriding.dev.user.infra.entity.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CommentClosureJpaRepositoryImpl implements CommentClosureJpaRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentClosureEntity> fetchAllAncestorsBy(ParentCommentId parentCommentId) {
        QCommentEntity ancestor = new QCommentEntity("ancestor");
        QCommentEntity descendant = new QCommentEntity("descendant");
        QUserEntity ancestorUser = new QUserEntity("ancestorUser");
        QUserEntity descendantUser = new QUserEntity("descendantUser");

        return queryFactory
                .select(commentClosureEntity)
                .from(commentClosureEntity)
                .innerJoin(commentClosureEntity.ancestor, ancestor).fetchJoin()
                .innerJoin(ancestor.user, ancestorUser).fetchJoin()
                .innerJoin(commentClosureEntity.descendant, descendant).fetchJoin()
                .innerJoin(descendant.user, descendantUser).fetchJoin()
                .where(
                        descendant.id.eq(parentCommentId.getId()),
                        ancestor.isDeleted.isFalse(),
                        descendant.isDeleted.isFalse()
                )
                .fetch();
    }

    @Override
    public List<NestedCommentStatResult> fetchNestedCommentStats(List<Long> commentIds) {
        QCommentEntity ancestor = new QCommentEntity("ancestor");
        QCommentEntity descendant = new QCommentEntity("descendant");
        CaseBuilder caseBuilder = new CaseBuilder();

        return queryFactory
                .select(Projections.constructor(
                        NestedCommentStatResult.class,
                        ancestor.id.as("id"),
                        caseBuilder
                                .when(ancestor.id.count().gt(1)).then(true)
                                .otherwise(false)
                                .as("nestedExists")
                ))
                .from(commentClosureEntity)
                .innerJoin(commentClosureEntity.ancestor, ancestor)
                .innerJoin(commentClosureEntity.descendant, descendant)
                .where(
                        ancestor.id.in(commentIds),
                        descendant.isDeleted.isFalse()
                )
                .groupBy(ancestor.id)
                .fetch();
    }

    @Override
    public List<NestedCommentItem> fetchNestedCommentsBy(CommentId commentId) {
        QCommentClosureEntity secondaryClosure = new QCommentClosureEntity("secondaryClosure");
        QCommentClosureEntity primaryClosure = new QCommentClosureEntity("primaryClosure");
        QCommentEntity secondaryComment = new QCommentEntity("secondaryComment");
        QCommentEntity primaryComment = new QCommentEntity("primaryComment");
        QUserEntity secondaryCommentAuthor = new QUserEntity("secondaryCommentAuthor");
        QUserEntity primaryCommentAuthor = new QUserEntity("primaryCommentAuthor");

        return queryFactory
                .select(Projections.constructor(
                        NestedCommentItem.class,
                        secondaryClosure.ancestor.id.as("ancestorId"),
                        primaryComment.id.as("primaryId"),
                        primaryComment.content.as("primaryContent"),
                        primaryComment.createdAt.as("primaryCreatedAt"),
                        primaryComment.updatedAt.as("primaryUpdatedAt"),
                        primaryClosure.depth.as("primaryDepth"),
                        primaryCommentAuthor.id.as("primaryUserId"),
                        primaryCommentAuthor.username.as("primaryUsername"),
                        secondaryComment.id.as("secondaryId"),
                        secondaryComment.content.as("secondaryContent"),
                        secondaryComment.createdAt.as("secondaryCreatedAt"),
                        secondaryComment.updatedAt.as("secondaryUpdatedAt"),
                        secondaryClosure.depth.as("secondaryDepth"),
                        secondaryCommentAuthor.id.as("secondaryUserId"),
                        secondaryCommentAuthor.username.as("secondaryUsername")

                ))
                .from(secondaryClosure)
                .innerJoin(primaryClosure).on(secondaryClosure.descendant.eq(primaryClosure.descendant))
                .innerJoin(primaryClosure.ancestor, primaryComment)
                .innerJoin(primaryComment.user, primaryCommentAuthor)
                .innerJoin(secondaryClosure.descendant, secondaryComment)
                .innerJoin(secondaryComment.user, secondaryCommentAuthor)
                .where(
                        secondaryClosure.ancestor.id.eq(commentId.getId()),
                        primaryClosure.depth.eq(1),
                        primaryComment.isDeleted.isFalse(),
                        secondaryComment.isDeleted.isFalse()
                )
                .fetch();
    }

    @Override
    public long countNestedCommentsBy(CommentId commentId) {
        QCommentEntity secondaryComment = new QCommentEntity("secondaryComment");
        QCommentEntity primaryComment = new QCommentEntity("primaryComment");

        Long fetchFirst = queryFactory
                .select(commentClosureEntity.id.count())
                .from(commentClosureEntity)
                .innerJoin(commentClosureEntity.descendant, secondaryComment)
                .innerJoin(commentClosureEntity.ancestor, primaryComment)
                .where(
                        primaryComment.id.eq(commentId.getId()),
                        commentClosureEntity.depth.ne(0),
                        primaryComment.isDeleted.isFalse(),
                        secondaryComment.isDeleted.isFalse()
                )
                .fetchFirst();

        return fetchFirst == null ? 0 : fetchFirst;
    }
}
