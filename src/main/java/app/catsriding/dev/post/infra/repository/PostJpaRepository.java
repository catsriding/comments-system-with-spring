package app.catsriding.dev.post.infra.repository;

import app.catsriding.dev.post.infra.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long>, PostJpaRepositoryExtension {

}