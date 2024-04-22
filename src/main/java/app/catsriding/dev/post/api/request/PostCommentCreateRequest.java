package app.catsriding.dev.post.api.request;

import app.catsriding.dev.comment.domain.value.CommentCreate;
import app.catsriding.dev.comment.domain.value.ParentCommentId;
import app.catsriding.dev.post.domain.value.PostCommentCreate;
import app.catsriding.dev.post.domain.value.PostId;
import app.catsriding.dev.user.domain.value.UserId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class PostCommentCreateRequest {

    @NotNull(message = "User ID is a required field")
    @Positive(message = "User ID must be a positive number")
    private final Long userId;

    @Positive(message = "Parent ID must be a positive number if provided")
    private final Long parentId;

    @NotBlank(message = "Content is a required field")
    @Size(max = 255, message = "Content must be less than or equal to 255 characters")
    private final String content;

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Saturday, 13 April 2024, 12:47PM KST
     */
    public PostCommentCreate toPostCommentCreate(Long postId) {
        return PostCommentCreate.builder()
                .postId(PostId.from(postId))
                .build();
    }

    public CommentCreate toCommentCreate() {
        return CommentCreate.builder()
                .userId(UserId.from(userId))
                .parentCommentId(ParentCommentId.from(parentId))
                .content(content)
                .isDeleted(false)
                .build();
    }
}
