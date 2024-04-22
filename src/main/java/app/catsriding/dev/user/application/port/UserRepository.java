package app.catsriding.dev.user.application.port;

import app.catsriding.dev.user.domain.model.User;
import app.catsriding.dev.user.domain.value.UserId;

public interface UserRepository {

    User fetchBy(UserId userId);

}
