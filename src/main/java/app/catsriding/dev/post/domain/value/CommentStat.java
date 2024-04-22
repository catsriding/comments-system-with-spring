package app.catsriding.dev.post.domain.value;

import app.catsriding.dev.comment.infra.result.NestedCommentStatResult;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CommentStat {

    private final Long id;
    private final boolean nestedExists;

    @Builder
    public CommentStat(Long id, boolean nestedExists) {
        this.id = id;
        this.nestedExists = nestedExists;
    }

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Saturday, 20 April 2024, 04:13PM KST
     */
    public static CommentStat from(NestedCommentStatResult result) {
        return CommentStat.builder()
                .id(result.getId())
                .nestedExists(result.isNestedExists())
                .build();
    }

}
