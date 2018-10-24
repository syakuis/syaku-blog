package org.syaku.blog.user.repository;

import org.springframework.data.repository.Repository;
import org.syaku.blog.user.domain.UserEntity;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 23/10/2018
 */
public interface UserRepository extends Repository<UserEntity, Long> {
  UserEntity findOneByUsername(String username);
  UserEntity save(UserEntity userEntity);
}
