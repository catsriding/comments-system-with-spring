package app.catsriding.dev.comment.domain.model;

import app.catsriding.dev.comment.domain.value.CommentCreate;
import app.catsriding.dev.user.domain.model.User;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Comment {

    private final Long id;
    private final User user;
    private final String content;
    private final boolean isDeleted;
    private final LocalDateTime updatedAt;
    private final LocalDateTime createdAt;

    @Builder
    public Comment(
            Long id,
            User user,
            String content,
            boolean isDeleted,
            LocalDateTime updatedAt,
            LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.isDeleted = isDeleted;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Friday, 12 April 2024, 02:57PM KST
     */
    public static Comment from(User user, CommentCreate commentCreate, LocalDateTime now) {
        return Comment.builder()
                .user(user)
                .content(commentCreate.getContent())
                .isDeleted(commentCreate.isDeleted())
                .updatedAt(now)
                .createdAt(now)
                .build();
    }
}