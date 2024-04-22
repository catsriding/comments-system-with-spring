package app.catsriding.dev.post.api;

import app.catsriding.dev.post.api.port.PostCommentService;
import app.catsriding.dev.post.api.request.PostCommentCreateRequest;
import app.catsriding.dev.post.api.request.PostCommentCreateResponse;
import app.catsriding.dev.post.api.request.PostCommentsRequest;
import app.catsriding.dev.post.api.response.PostCommentsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts/{postId}/comments")
public class PostCommentController {

    private final PostCommentService service;

    @PostMapping
    public ResponseEntity<?> postsCommentCreateApi(
            @PathVariable Long postId,
            @Valid @RequestBody PostCommentCreateRequest request) {
        PostCommentCreateResponse response = service.createPostComment(
                request.toCommentCreate(),
                request.toPostCommentCreate(postId));
        return ResponseEntity
                .ok(response);
    }

    @GetMapping
    public ResponseEntity<?> postsCommentsApi(
            @PathVariable Long postId) {
        PostCommentsRequest request = PostCommentsRequest.from(postId);
        PostCommentsResponse response = service.retrievePostComments(request.toPostId());
        return ResponseEntity
                .ok(response);
    }

}
