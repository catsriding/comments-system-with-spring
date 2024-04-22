package app.catsriding.dev.comment.infra.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CommentJpaRepositoryImpl implements CommentJpaRepositoryExtension {

    private final JPAQueryFactory queryFactory;

}