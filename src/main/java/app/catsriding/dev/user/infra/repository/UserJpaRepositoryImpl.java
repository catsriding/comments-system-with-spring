package app.catsriding.dev.user.infra.repository;

import static app.catsriding.dev.user.infra.entity.QUserEntity.userEntity;

import app.catsriding.dev.user.domain.value.UserId;
import app.catsriding.dev.user.infra.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserJpaRepositoryImpl implements UserJpaRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<UserEntity> fetchBuId(UserId userId) {
        return Optional.ofNullable(
                queryFactory
                        .select(userEntity)
                        .from(userEntity)
                        .where(userEntity.id.eq(userId.getId()))
                        .fetchOne());
    }
}
