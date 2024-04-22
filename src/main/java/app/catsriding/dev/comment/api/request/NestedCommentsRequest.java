package app.catsriding.dev.comment.api.request;

import app.catsriding.dev.comment.domain.value.CommentId;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class NestedCommentsRequest {

    private final Long commentId;

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Monday, 22 April 2024, 09:58PM KST
     */
    public static NestedCommentsRequest from(Long commentId) {
        return NestedCommentsRequest.builder()
                .commentId(commentId)
                .build();
    }

    public CommentId toCommentId() {
        return CommentId.builder()
                .id(commentId)
                .build();
    }
}
