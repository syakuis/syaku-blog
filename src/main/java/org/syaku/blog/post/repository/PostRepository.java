package org.syaku.blog.post.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.syaku.blog.post.domain.PostEntity;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 9.
 */
public interface PostRepository extends Repository<PostEntity, Long> {
  List<PostEntity> findAll();
  PostEntity findById(long id);
  PostEntity save(PostEntity postEntity);
  void deleteById(long id);
}
