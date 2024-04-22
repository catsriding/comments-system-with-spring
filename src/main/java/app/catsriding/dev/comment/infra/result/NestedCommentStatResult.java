package app.catsriding.dev.comment.infra.result;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class NestedCommentStatResult {

    private final Long id;
    private final boolean nestedExists;

    public NestedCommentStatResult(Long id, boolean nestedExists) {
        this.id = id;
        this.nestedExists = nestedExists;
    }
}
