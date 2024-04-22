package app.catsriding.dev.post.infra.repository;

import static app.catsriding.dev.post.infra.entity.QPostEntity.postEntity;
import static app.catsriding.dev.user.infra.entity.QUserEntity.userEntity;

import app.catsriding.dev.post.domain.value.PostId;
import app.catsriding.dev.post.infra.entity.PostEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PostJpaRepositoryImpl implements PostJpaRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PostEntity> fetchBy(PostId postId) {
        return Optional.ofNullable(
                queryFactory
                        .select(postEntity)
                        .from(postEntity)
                        .innerJoin(postEntity.user, userEntity).fetchJoin()
                        .where(
                                postEntity.id.eq(postId.getId()),
                                postEntity.isDeleted.isFalse())
                        .fetchOne());
    }

}
