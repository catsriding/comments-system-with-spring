package app.catsriding.dev.comment.api.response;

import app.catsriding.dev.common.application.helper.StreamHelper;
import app.catsriding.dev.comment.domain.value.NestedCommentItem;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class NestedCommentsResponse {

    private final List<NestedComment> data;
    private final Long totalCount;

    public static NestedCommentsResponse from(List<NestedCommentItem> comments, long totalCount) {
        return NestedCommentsResponse.builder()
                .data(StreamHelper.transform(comments, NestedComment::from))
                .totalCount(totalCount)
                .build();
    }

    @Slf4j
    @Getter
    @Builder
    private static class NestedComment {

        private final Long ancestorId;
        private final Long id;
        private final Author author;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final PrimaryComment primary;
        private final Integer depth;

        private static NestedComment from(NestedCommentItem item) {
            return NestedComment.builder()
                    .ancestorId(item.getAncestorId())
                    .id(item.getSecondaryId())
                    .content(item.getSecondaryContent())
                    .author(Author.secondary(item))
                    .depth(item.getSecondaryDepth())
                    .primary(PrimaryComment.from(item))
                    .createdAt(item.getSecondaryCreatedAt())
                    .updatedAt(item.getSecondaryUpdatedAt())
                    .build();
        }

    }

    @Slf4j
    @Getter
    @Builder
    private static class PrimaryComment {

        private final Long id;
        private final Author author;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        private static PrimaryComment from(NestedCommentItem item) {
            return PrimaryComment.builder()
                    .id(item.getPrimaryId())
                    .content(item.getPrimaryContent())
                    .author(Author.primary(item))
                    .createdAt(item.getPrimaryCreatedAt())
                    .updatedAt(item.getPrimaryUpdatedAt())
                    .build();
        }
    }

    @Slf4j
    @Getter
    @Builder
    private static class Author {

        private final Long id;
        private final String username;

        private static Author primary(NestedCommentItem item) {
            return Author.builder()
                    .id(item.getPrimaryUserId())
                    .username(item.getPrimaryUsername())
                    .build();
        }

        private static Author secondary(NestedCommentItem item) {
            return Author.builder()
                    .id(item.getSecondaryUserId())
                    .username(item.getSecondaryUsername())
                    .build();
        }
    }
}
