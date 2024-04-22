package app.catsriding.dev.post.api.request;

import app.catsriding.dev.post.application.exception.PostCommentRequestValidationException;
import app.catsriding.dev.post.domain.value.PostId;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class PostCommentsRequest {

    private final Long postId;

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Saturday, 20 April 2024, 02:48PM KST
     */
    public static PostCommentsRequest from(Long postId) {
        validate(postId);
        return PostCommentsRequest.builder()
                .postId(postId)
                .build();
    }

    private static void validate(Long postId) {
        if (postId == null || postId < 1) {
            throw new PostCommentRequestValidationException("Invalid ID");
        }
    }

    public PostId toPostId() {
        return PostId.builder()
                .id(postId)
                .build();
    }
}
