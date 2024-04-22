package app.catsriding.dev.post.domain.value;

import app.catsriding.dev.post.infra.result.PostCommentItemResult;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PostCommentItem {

    private final Long postId;
    private final Long userId;
    private final Long commentId;
    private final Long ancestorId;
    private final String username;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    private PostCommentItem(
            Long postId,
            Long userId,
            Long commentId, Long ancestorId,
            String username,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.commentId = commentId;
        this.ancestorId = ancestorId;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Saturday, 20 April 2024, 03:39PM KST
     */
    public static List<PostCommentItem> from(List<PostCommentItemResult> results) {
        return results.stream()
                .map(PostCommentItem::from)
                .collect(Collectors.toList());
    }

    private static PostCommentItem from(PostCommentItemResult result) {
        return PostCommentItem.builder()
                .postId(result.getPostId())
                .commentId(result.getCommentId())
                .ancestorId(result.getAncestorId())
                .userId(result.getUserId())
                .username(result.getUsername())
                .content(result.getContent())
                .createdAt(result.getCreatedAt())
                .updatedAt(result.getUpdatedAt())
                .build();
    }

}
