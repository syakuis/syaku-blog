package org.syaku.blog.post.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.blog.post.domain.Post;
import org.syaku.blog.post.domain.PostEntity;
import org.syaku.blog.post.repository.PostRepository;

/**
 * todo 테스트에서만 사용되고 실제 사용되지 않는 기능을 어디에 위해야하나?
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 15.
 */
@Service
@Transactional
public class PostService {
  private PostRepository postRepository;

  @Autowired
  public void setPostRepository(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  private List<Post> getImmutable(List<PostEntity> postEntities) {
    return Collections.unmodifiableList(
      postEntities.stream().collect(Collectors.toList()));
  }

  /**
   * 포스트 전체 데이터를 불변 목록으로 반환한다.
   * @return List
   */
  public List<Post> getPostList() {
    return this.getPostList(new Sort(Sort.Direction.DESC, "id"));
  }

  public List<Post> getPostList(Sort sort) {
    return getImmutable(postRepository.findAll(sort));
  }

  public Post save(PostEntity postEntity) {
    return postRepository.save(postEntity);
  }

  public Post getPost(long id) {
    return postRepository.findById(id);
  }

  public void delete(long id) {
    postRepository.deleteById(id);
  }
}
