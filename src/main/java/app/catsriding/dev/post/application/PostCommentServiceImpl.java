package app.catsriding.dev.post.application;

import static app.catsriding.dev.common.application.helper.StreamHelper.transform;
import static app.catsriding.dev.post.infra.cond.PostCommentPageableCond.from;

import app.catsriding.dev.comment.api.port.CommentService;
import app.catsriding.dev.comment.domain.model.Comment;
import app.catsriding.dev.comment.domain.value.CommentCreate;
import app.catsriding.dev.common.application.port.ClockHolder;
import app.catsriding.dev.post.api.port.PostCommentService;
import app.catsriding.dev.post.api.port.PostService;
import app.catsriding.dev.post.api.request.PostCommentCreateResponse;
import app.catsriding.dev.post.api.response.PostCommentsResponse;
import app.catsriding.dev.post.application.port.PostCommentRepository;
import app.catsriding.dev.post.domain.model.Post;
import app.catsriding.dev.post.domain.model.PostComment;
import app.catsriding.dev.post.domain.value.PostCommentCreate;
import app.catsriding.dev.post.domain.value.PostCommentItem;
import app.catsriding.dev.post.domain.value.PostId;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentRepository postCommentRepository;

    private final PostService postService;
    private final CommentService commentService;
    private final ClockHolder clock;

    @Override
    @Transactional
    public PostCommentCreateResponse createPostComment(
            CommentCreate commentCreate,
            PostCommentCreate postCommentCreate) {
        Post post = postService.retrievePostById(postCommentCreate.getPostId());
        Comment comment = commentService.createComment(commentCreate);
        PostComment postComment = postCommentRepository.save(PostComment.from(post, comment, clock.now()));

        log.info(
                "createPostComment: Successfully created post comment - postId={} commentId={} postCommentId={}",
                post.getId(),
                comment.getId(),
                postComment.getId());

        return PostCommentCreateResponse.response(post.getId(), comment.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public PostCommentsResponse retrievePostComments(PostId postId) {
        List<PostCommentItem> comments = postCommentRepository.fetchBy(from(postId));
        List<Long> commentIds = transform(comments, PostCommentItem::getCommentId);
        Map<Long, Boolean> statuses = commentService.hasNestedComments(commentIds);
        long totalCount = postCommentRepository.countTotal(postId);

        return PostCommentsResponse.from(comments, statuses, totalCount);
    }

}
