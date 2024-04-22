package app.catsriding.dev.post.domain.value;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PostId {

    private final Long id;

    @Builder
    public PostId(Long id) {
        this.id = id;
    }

    public static PostId from(Long postId) {
        return PostId.builder()
                .id(postId)
                .build();
    }
}
