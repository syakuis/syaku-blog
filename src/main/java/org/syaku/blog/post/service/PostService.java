package org.syaku.blog.post.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.blog.post.domain.Post;
import org.syaku.blog.post.domain.PostEntity;
import org.syaku.blog.post.repository.PostRepository;

/**
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

  /**
   * 포스트 전체 데이터를 불변 목록으로 반환한다.
   * @return List
   */
  public List<Post> getPostList() {
    return Collections.unmodifiableList(
      postRepository.findAll().stream().collect(Collectors.toList()));
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
