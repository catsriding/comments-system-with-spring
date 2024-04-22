package app.catsriding.dev.user.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private final Long id;
    private final String username;
    private final LocalDateTime updatedAt;
    private final LocalDateTime createdAt;

    @Builder
    public User(Long id, String username, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }
}