package app.catsriding.dev.post.infra.entity;

import app.catsriding.dev.comment.infra.entity.CommentEntity;
import app.catsriding.dev.post.domain.model.PostComment;
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
@Table(name = "POST_COMMENTS")
public class PostCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_id", nullable = false)
    private CommentEntity comment;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public static PostCommentEntity from(PostComment postComment) {
        PostCommentEntity entity = new PostCommentEntity();
        entity.post = PostEntity.from(postComment.getPost());
        entity.comment = CommentEntity.from(postComment.getComment());
        entity.isDeleted = postComment.isDeleted();
        entity.updatedAt = postComment.getUpdatedAt();
        entity.createdAt = postComment.getCreatedAt();
        return entity;
    }

    public PostComment toModel() {
        return PostComment.builder()
                .id(id)
                .post(post.toModel())
                .comment(comment.toModel())
                .isDeleted(isDeleted)
                .updatedAt(createdAt)
                .createdAt(updatedAt)
                .build();
    }
}