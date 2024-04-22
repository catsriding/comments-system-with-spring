package app.catsriding.dev.post.infra.cond;

import app.catsriding.dev.post.domain.value.PostId;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PostCommentPageableCond {

    private final Long postId;
    private final boolean isDeleted;

    @Builder
    public PostCommentPageableCond(Long postId, boolean isDeleted) {
        this.postId = postId;
        this.isDeleted = isDeleted;
    }

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Saturday, 20 April 2024, 03:06PM KST
     */
    public static PostCommentPageableCond from(PostId postId) {
        return PostCommentPageableCond.builder()
                .postId(postId.getId())
                .isDeleted(false)
                .build();
    }

}
