package app.catsriding.dev.post.application.port;

import app.catsriding.dev.post.domain.model.Post;
import app.catsriding.dev.post.domain.value.PostId;

public interface PostRepository {

    Post fetchBy(PostId postId);

}
