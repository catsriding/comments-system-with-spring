package app.catsriding.dev.comment.domain.value;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class NestedCommentItem {

    private final Long ancestorId;
    private final Long primaryId;
    private final String primaryContent;
    private final LocalDateTime primaryCreatedAt;
    private final LocalDateTime primaryUpdatedAt;
    private final Integer primaryDepth;
    private final Long primaryUserId;
    private final String primaryUsername;
    private final Long secondaryId;
    private final String secondaryContent;
    private final LocalDateTime secondaryCreatedAt;
    private final LocalDateTime secondaryUpdatedAt;
    private final Integer secondaryDepth;
    private final Long secondaryUserId;
    private final String secondaryUsername;

    public NestedCommentItem(
            Long ancestorId,
            Long primaryId,
            String primaryContent,
            LocalDateTime primaryCreatedAt,
            LocalDateTime primaryUpdatedAt,
            Integer primaryDepth,
            Long primaryUserId,
            String primaryUsername,
            Long secondaryId,
            String secondaryContent,
            LocalDateTime secondaryCreatedAt,
            LocalDateTime secondaryUpdatedAt,
            Integer secondaryDepth,
            Long secondaryUserId,
            String secondaryUsername) {
        this.ancestorId = ancestorId;
        this.primaryId = primaryId;
        this.primaryContent = primaryContent;
        this.primaryCreatedAt = primaryCreatedAt;
        this.primaryUpdatedAt = primaryUpdatedAt;
        this.primaryDepth = primaryDepth;
        this.primaryUserId = primaryUserId;
        this.primaryUsername = primaryUsername;
        this.secondaryId = secondaryId;
        this.secondaryContent = secondaryContent;
        this.secondaryCreatedAt = secondaryCreatedAt;
        this.secondaryUpdatedAt = secondaryUpdatedAt;
        this.secondaryDepth = secondaryDepth;
        this.secondaryUserId = secondaryUserId;
        this.secondaryUsername = secondaryUsername;
    }
}
