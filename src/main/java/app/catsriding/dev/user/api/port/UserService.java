package app.catsriding.dev.user.api.port;

import app.catsriding.dev.user.domain.model.User;
import app.catsriding.dev.user.domain.value.UserId;

public interface UserService {

    User retrieveUserById(UserId userId);

}
