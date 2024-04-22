package app.catsriding.dev.post.infra.result;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PostCommentItemResult {

    private final Long postId;
    private final Long userId;
    private final String username;
    private final Long commentId;
    private final Long ancestorId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public PostCommentItemResult(
            Long postId,
            Long userId,
            String username,
            Long commentId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.commentId = commentId;
        this.ancestorId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
