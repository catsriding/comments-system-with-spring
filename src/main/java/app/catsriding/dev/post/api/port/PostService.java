package app.catsriding.dev.post.api.port;

import app.catsriding.dev.post.domain.model.Post;
import app.catsriding.dev.post.domain.value.PostId;

public interface PostService {

    Post retrievePostById(PostId postId);

}
