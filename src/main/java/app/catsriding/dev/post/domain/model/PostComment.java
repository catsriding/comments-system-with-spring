package app.catsriding.dev.post.domain.model;

import app.catsriding.dev.comment.domain.model.Comment;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostComment {

    private final Long id;
    private final Post post;
    private final Comment comment;
    private final boolean isDeleted;
    private final LocalDateTime updatedAt;
    private final LocalDateTime createdAt;

    @Builder
    private PostComment(
            Long id,
            Post post,
            Comment comment, boolean isDeleted,
            LocalDateTime updatedAt,
            LocalDateTime createdAt) {
        this.id = id;
        this.post = post;
        this.comment = comment;
        this.isDeleted = isDeleted;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Friday, 12 April 2024, 10:26PM KST
     */
    public static PostComment from(Post post, Comment comment, LocalDateTime now) {
        return PostComment.builder()
                .post(post)
                .comment(comment)
                .isDeleted(false)
                .updatedAt(now)
                .createdAt(now)
                .build();
    }
}