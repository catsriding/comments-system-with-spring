package app.catsriding.dev.post.infra.repository;

import static app.catsriding.dev.comment.infra.entity.QCommentClosureEntity.commentClosureEntity;
import static app.catsriding.dev.comment.infra.entity.QCommentEntity.commentEntity;
import static app.catsriding.dev.post.infra.entity.QPostCommentEntity.postCommentEntity;
import static app.catsriding.dev.post.infra.entity.QPostEntity.postEntity;
import static app.catsriding.dev.user.infra.entity.QUserEntity.userEntity;

import app.catsriding.dev.post.domain.value.PostId;
import app.catsriding.dev.post.infra.cond.PostCommentPageableCond;
import app.catsriding.dev.post.infra.result.PostCommentItemResult;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PostCommentJpaRepositoryImpl implements PostCommentJpaRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostCommentItemResult> fetchBy(PostCommentPageableCond cond) {
        return queryFactory
                .select(Projections.constructor(
                        PostCommentItemResult.class,
                        postEntity.id.as("postId"),
                        userEntity.id.as("userId"),
                        userEntity.username.as("username"),
                        commentEntity.id.as("commentId"),
                        commentEntity.content.as("content"),
                        commentEntity.createdAt.as("createdAt"),
                        commentEntity.updatedAt.as("updatedAt")
                ))
                .from(postCommentEntity)
                .innerJoin(postCommentEntity.post, postEntity)
                .innerJoin(postCommentEntity.comment, commentEntity)
                .innerJoin(commentEntity.user, userEntity)
                .leftJoin(commentClosureEntity)
                .on(commentClosureEntity.descendant.eq(commentEntity).and(commentClosureEntity.depth.gt(0)))
                .where(
                        postEntity.id.eq(cond.getPostId()),
                        commentClosureEntity.id.isNull(),
                        postEntity.isDeleted.isFalse(),
                        commentEntity.isDeleted.isFalse()
                )
                .fetch();
    }

    @Override
    public long countTotal(PostId postId) {
        return queryFactory
                .select(postCommentEntity.id.count())
                .from(postCommentEntity)
                .innerJoin(postCommentEntity.post, postEntity)
                .innerJoin(postCommentEntity.comment, commentEntity)
                .where(
                        postEntity.id.eq(postId.getId()),
                        postEntity.isDeleted.isFalse(),
                        commentEntity.isDeleted.isFalse(),
                        postCommentEntity.isDeleted.isFalse()
                )
                .fetchFirst();
    }

}
