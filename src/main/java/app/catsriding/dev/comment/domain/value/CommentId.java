package app.catsriding.dev.comment.domain.value;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class CommentId {

    private final Long id;

}
