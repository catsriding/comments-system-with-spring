package app.catsriding.dev.comment.infra.entity;

import app.catsriding.dev.comment.domain.model.Comment;
import app.catsriding.dev.user.infra.entity.UserEntity;
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
@Table(name = "COMMENTS")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Saturday, 13 April 2024, 11:52AM KST
     */
    public static CommentEntity from(Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.id = comment.getId();
        entity.user = UserEntity.from(comment.getUser());
        entity.content = comment.getContent();
        entity.isDeleted = comment.isDeleted();
        entity.updatedAt = comment.getUpdatedAt();
        entity.createdAt = comment.getCreatedAt();
        return entity;
    }

    public Comment toModel() {
        return Comment.builder()
                .id(id)
                .user(user.toModel())
                .content(content)
                .isDeleted(isDeleted)
                .updatedAt(updatedAt)
                .createdAt(createdAt)
                .build();
    }
}