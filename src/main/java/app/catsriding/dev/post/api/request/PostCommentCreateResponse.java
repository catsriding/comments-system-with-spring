package app.catsriding.dev.post.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class PostCommentCreateResponse {

    private final Long postId;
    private final Long commentId;
    private final String message;

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Saturday, 13 April 2024, 12:20PM KST
     */
    public static PostCommentCreateResponse response(Long postId, Long commentId) {
        return PostCommentCreateResponse.builder()
                .postId(postId)
                .commentId(commentId)
                .message("Successfully added a new post comment")
                .build();
    }
}
