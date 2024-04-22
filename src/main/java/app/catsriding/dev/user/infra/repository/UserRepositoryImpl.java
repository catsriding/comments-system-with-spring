package app.catsriding.dev.user.infra.repository;

import app.catsriding.dev.post.application.exception.PostCommentAdditionException;
import app.catsriding.dev.user.application.port.UserRepository;
import app.catsriding.dev.user.domain.model.User;
import app.catsriding.dev.user.domain.value.UserId;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public User fetchBy(UserId userId) {
        return userJpaRepository.fetchBuId(userId)
                .orElseThrow(exception(userId))
                .toModel();
    }

    private static Supplier<RuntimeException> exception(UserId userId) {
        return () -> {
            log.info("fetchBy: Does not found user by userId={}", userId.getId());
            return new PostCommentAdditionException(
                    "Unable to retrieve the user for adding a comment. "
                    + "Please verify that the post exists and try again.");
        };
    }

}
