package app.catsriding.dev.user.infra.entity;

import app.catsriding.dev.user.domain.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 60)
    private String username;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public static UserEntity from(User user) {
        UserEntity entity = new UserEntity();
        entity.id = user.getId();
        entity.username = user.getUsername();
        entity.updatedAt = user.getUpdatedAt();
        entity.createdAt = user.getCreatedAt();
        return entity;
    }

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Friday, 12 April 2024, 03:57PM KST
     */
    public User toModel() {
        return User.builder()
                .id(id)
                .username(username)
                .updatedAt(updatedAt)
                .createdAt(createdAt)
                .build();
    }
}