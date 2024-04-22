package app.catsriding.dev.post.api.response;

import app.catsriding.dev.post.domain.value.PostCommentItem;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class PostCommentsResponse {

    private final List<Comment> data;
    private final long totalCount;

    /**
     * <h2>🏗️ Constructors</h2>
     *
     * @author Jynn ❖
     * @since Saturday, 20 April 2024, 05:07PM KST
     */
    public static PostCommentsResponse from(
            List<PostCommentItem> comments,
            Map<Long, Boolean> statuses,
            long totalCount) {
        List<Comment> data = comments.stream()
                .map(element -> Comment.from(element, statuses.get(element.getCommentId())))
                .toList();

        return PostCommentsResponse.builder()
                .data(data)
                .totalCount(totalCount)
                .build();
    }

    @Getter
    @Builder
    private static class Comment {

        private final Long id;
        private final Author author;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final boolean hasNested;

        private static Comment from(PostCommentItem comment, boolean hasNested) {
            return Comment.builder()
                    .id(comment.getCommentId())
                    .author(Author.from(comment))
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .hasNested(hasNested)
                    .build();
        }
    }

    @Getter
    @Builder
    private static class Author {

        private final Long id;
        private final String username;

        private static Author from(PostCommentItem comment) {
            return Author.builder()
                    .id(comment.getUserId())
                    .username(comment.getUsername())
                    .build();
        }
    }

}
