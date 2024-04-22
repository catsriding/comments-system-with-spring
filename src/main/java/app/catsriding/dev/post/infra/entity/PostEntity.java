package app.catsriding.dev.post.infra.entity;

import app.catsriding.dev.post.domain.model.Post;
import app.catsriding.dev.user.infra.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "POSTS")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public static PostEntity from(Post post) {
        PostEntity entity = new PostEntity();
        entity.id = post.getId();
        entity.user = UserEntity.from(post.getUser());
        entity.title = post.getTitle();
        entity.content = post.getContent();
        entity.isDeleted = post.isDeleted();
        entity.updatedAt = post.getUpdatedAt();
        entity.createdAt = post.getCreatedAt();
        return entity;
    }

    public Post toModel() {
        return Post.builder()
                .id(id)
                .user(user.toModel())
                .title(title)
                .content(content)
                .isDeleted(isDeleted)
                .updatedAt(updatedAt)
                .createdAt(createdAt)
                .build();
    }
}