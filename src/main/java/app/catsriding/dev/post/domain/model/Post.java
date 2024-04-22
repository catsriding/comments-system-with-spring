package app.catsriding.dev.post.domain.model;

import app.catsriding.dev.user.domain.model.User;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {

    private final Long id;
    private final User user;
    private final String title;
    private final String content;
    private final boolean isDeleted;
    private final LocalDateTime updatedAt;
    private final LocalDateTime createdAt;

    @Builder
    public Post(
            Long id,
            User user,
            String title,
            String content, boolean isDeleted,
            LocalDateTime updatedAt,
            LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.isDeleted = isDeleted;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }
}