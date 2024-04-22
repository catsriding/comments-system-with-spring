package app.catsriding.dev.user.infra.repository;

import app.catsriding.dev.user.domain.value.UserId;
import app.catsriding.dev.user.infra.entity.UserEntity;
import java.util.Optional;

public interface UserJpaRepositoryExtension {

    Optional<UserEntity> fetchBuId(UserId userId);

}
