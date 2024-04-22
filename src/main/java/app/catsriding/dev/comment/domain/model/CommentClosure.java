package app.catsriding.dev.comment.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentClosure {

    private final Long id;
    private final Comment ancestor;
    private final Comment descendant;
    private final Integer depth;
    private final LocalDateTime updatedAt;
    private final LocalDateTime createdAt;

    @Builder
    private CommentClosure(
            Long id,
            Comment ancestor,
            Comment descendant,
            Integer depth,
            LocalDateTime updatedAt,
            LocalDateTime createdAt) {
        this.id = id;
        this.ancestor = ancestor;
        this.descendant = descendant;
        this.depth = depth;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Friday, 12 April 2024, 10:29PM KST
     */
    public static CommentClosure initClosure(Comment selfNode, LocalDateTime now) {
        return CommentClosure.builder()
                .ancestor(selfNode)
                .descendant(selfNode)
                .depth(initializeDepth())
                .updatedAt(now)
                .createdAt(now)
                .build();
    }

    public static CommentClosure mergeClosure(
            CommentClosure ancestorNode,
            CommentClosure descendantNode,
            LocalDateTime now) {
        return CommentClosure.builder()
                .ancestor(ancestorNode.getAncestor())
                .descendant(descendantNode.getDescendant())
                .depth(increaseDepth(ancestorNode.getDepth()))
                .updatedAt(now)
                .createdAt(now)
                .build();
    }

    private static int initializeDepth() {
        return 0;
    }

    private static int increaseDepth(Integer depth) {
        return depth + 1;
    }
}