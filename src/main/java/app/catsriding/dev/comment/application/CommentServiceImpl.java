package app.catsriding.dev.comment.application;

import app.catsriding.dev.comment.api.port.CommentService;
import app.catsriding.dev.comment.api.response.NestedCommentsResponse;
import app.catsriding.dev.comment.application.port.CommentClosureRepository;
import app.catsriding.dev.comment.application.port.CommentRepository;
import app.catsriding.dev.comment.domain.model.Comment;
import app.catsriding.dev.comment.domain.model.CommentClosure;
import app.catsriding.dev.comment.domain.value.CommentCreate;
import app.catsriding.dev.comment.domain.value.CommentId;
import app.catsriding.dev.comment.domain.value.NestedCommentItem;
import app.catsriding.dev.common.application.port.ClockHolder;
import app.catsriding.dev.post.domain.value.CommentStat;
import app.catsriding.dev.user.api.port.UserService;
import app.catsriding.dev.user.domain.model.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentClosureRepository commentClosureRepository;

    private final UserService userService;
    private final ClockHolder clock;

    @Transactional
    public Comment createComment(CommentCreate commentCreate) {
        User user = userService.retrieveUserById(commentCreate.getUserId());
        Comment comment = Comment.from(user, commentCreate, clock.now());
        comment = commentRepository.save(comment);

        CommentClosure selfClosure = CommentClosure.initClosure(comment, clock.now());
        selfClosure = commentClosureRepository.save(selfClosure);

        linkClosures(commentCreate, selfClosure);

        return comment;
    }

    @Override
    public Map<Long, Boolean> hasNestedComments(List<Long> commentIds) {
        List<CommentStat> stats = commentClosureRepository.retrieveCommentStatsBy(commentIds);
        return stats.stream()
                .collect(Collectors.toMap(CommentStat::getId, CommentStat::isNestedExists));
    }

    private void linkClosures(CommentCreate commentCreate, CommentClosure descendant) {
        if (!commentCreate.hasParentId()) return;

        List<Long> ids = commentClosureRepository.fetchAncestors(commentCreate.getParentCommentId())
                .stream()
                .map(ancestor -> CommentClosure.mergeClosure(ancestor, descendant, clock.now()))
                .map(commentClosureRepository::save)
                .map(CommentClosure::getId)
                .toList();

        log.info("linkClosures: Successfully created closures - ids={}", ids);
    }

    @Override
    @Transactional(readOnly = true)
    public NestedCommentsResponse retrieveNestedComments(CommentId commentId) {
        List<NestedCommentItem> comments = commentClosureRepository.retrieveNestedComments(commentId);
        long totalCount = commentClosureRepository.countTotal(commentId);

        return NestedCommentsResponse.from(comments, totalCount);
    }

}
