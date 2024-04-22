package app.catsriding.dev.user.infra.repository;

import app.catsriding.dev.user.infra.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long>, UserJpaRepositoryExtension {

}