package app.catsriding.dev.comment.infra.entity;

import app.catsriding.dev.comment.domain.model.CommentClosure;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENT_CLOSURE")
public class CommentClosureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ancestor_id", nullable = false)
    private CommentEntity ancestor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "descendant_id", nullable = false)
    private CommentEntity descendant;

    @Column(name = "depth", nullable = false)
    private Integer depth;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public static CommentClosureEntity from(CommentClosure commentClosure) {
        CommentClosureEntity entity = new CommentClosureEntity();
        entity.ancestor = CommentEntity.from(commentClosure.getAncestor());
        entity.descendant = CommentEntity.from(commentClosure.getDescendant());
        entity.depth = commentClosure.getDepth();
        entity.updatedAt = commentClosure.getUpdatedAt();
        entity.createdAt = commentClosure.getCreatedAt();
        return entity;
    }

    public CommentClosure toModel() {
        return CommentClosure.builder()
                .id(id)
                .ancestor(ancestor.toModel())
                .descendant(descendant.toModel())
                .depth(depth)
                .updatedAt(updatedAt)
                .createdAt(createdAt)
                .build();
    }
}