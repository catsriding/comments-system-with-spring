package app.catsriding.dev.user.domain.value;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class UserId {

    private final Long id;

    @Builder
    public UserId(Long id) {
        this.id = id;
    }

    public static UserId from(Long userId) {
        return UserId.builder()
                .id(userId)
                .build();
    }
}
