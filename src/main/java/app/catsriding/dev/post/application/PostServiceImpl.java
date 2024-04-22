package app.catsriding.dev.post.application;

import app.catsriding.dev.post.api.port.PostService;
import app.catsriding.dev.post.application.port.PostRepository;
import app.catsriding.dev.post.domain.model.Post;
import app.catsriding.dev.post.domain.value.PostId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public Post retrievePostById(PostId postId) {
        return postRepository.fetchBy(postId);
    }
}
