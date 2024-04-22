package app.catsriding.dev.comment.api;

import app.catsriding.dev.comment.api.port.CommentService;
import app.catsriding.dev.comment.api.request.NestedCommentsRequest;
import app.catsriding.dev.comment.api.response.NestedCommentsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments")
public class CommentController {

    private final CommentService service;

    @GetMapping("/{commentId}")
    public ResponseEntity<?> postsNestedCommentsApi(@PathVariable Long commentId) {
        NestedCommentsRequest request = NestedCommentsRequest.from(commentId);
        NestedCommentsResponse response = service.retrieveNestedComments(request.toCommentId());
        return ResponseEntity
                .ok(response);
    }

}
