package app.catsriding.dev.comment.domain.value;

import app.catsriding.dev.user.domain.value.UserId;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CommentCreate {

    private final UserId userId;
    private final ParentCommentId parentCommentId;
    private final String content;
    private final boolean isDeleted;

    @Builder
    public CommentCreate(UserId userId, ParentCommentId parentCommentId, String content, boolean isDeleted) {
        this.userId = userId;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.isDeleted = isDeleted;
    }

    public boolean hasParentId() {
        return parentCommentId != null && parentCommentId.getId() != null;
    }

}
