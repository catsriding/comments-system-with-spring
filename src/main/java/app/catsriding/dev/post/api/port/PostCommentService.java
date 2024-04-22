package app.catsriding.dev.post.api.port;

import app.catsriding.dev.comment.domain.value.CommentCreate;
import app.catsriding.dev.post.api.request.PostCommentCreateResponse;
import app.catsriding.dev.post.api.response.PostCommentsResponse;
import app.catsriding.dev.post.domain.value.PostCommentCreate;
import app.catsriding.dev.post.domain.value.PostId;

public interface PostCommentService {

    PostCommentCreateResponse createPostComment(CommentCreate commentCreate, PostCommentCreate postCommentCreate);

    PostCommentsResponse retrievePostComments(PostId postId);

}
