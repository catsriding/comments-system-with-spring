package app.catsriding.dev.comment.domain.value;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ParentCommentId {

    private final Long id;

    @Builder
    private ParentCommentId(Long id) {
        this.id = id;
    }

    public static ParentCommentId from(Long commentId) {
        return ParentCommentId.builder()
                .id(commentId)
                .build();
    }
}
