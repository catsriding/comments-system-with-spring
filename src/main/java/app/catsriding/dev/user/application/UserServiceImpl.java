package app.catsriding.dev.user.application;

import app.catsriding.dev.user.api.port.UserService;
import app.catsriding.dev.user.application.port.UserRepository;
import app.catsriding.dev.user.domain.model.User;
import app.catsriding.dev.user.domain.value.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User retrieveUserById(UserId userId) {
        return userRepository.fetchBy(userId);
    }

}
