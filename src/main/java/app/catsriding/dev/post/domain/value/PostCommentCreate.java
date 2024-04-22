package app.catsriding.dev.post.domain.value;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PostCommentCreate {

    private final PostId postId;

    @Builder
    public PostCommentCreate(PostId postId) {
        this.postId = postId;
    }

}
