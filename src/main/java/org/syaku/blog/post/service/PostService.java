package org.syaku.blog.post.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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

  private List<PostEntity> getImmutable(List<PostEntity> postEntities) {
    return Collections.unmodifiableList(postEntities);
  }

  private Sort getSort() {
      return new Sort(Sort.Direction.DESC, "id");
  }

  private Pageable getPageable() {
    return getPageable(0);
  }
  private Pageable getPageable(int page) {
    return PageRequest.of(page, 10, getSort());
  }

  public List<PostEntity> getPostList() {
    return this.getPostList(getSort());
  }

  public List<PostEntity> getPostList(Sort sort) {
    return getImmutable(postRepository.findAll(sort));
  }

  public Page<PostEntity> getPostPaging() {
    return getPostPaging(getPageable());
  }

  public Page<PostEntity> getPostPaging(int page) {
    return getPostPaging(getPageable(page));
  }

  public Page<PostEntity> getPostPaging(Pageable pageable) {
    Page<PostEntity> page = postRepository.findAll(pageable);
    return new PageImpl<>(getImmutable(page.getContent()), pageable, page.getTotalElements());
  }

  public Page<PostEntity> getSearchPostPaging(String subject) {
    return getSearchPostPaging(subject, getPageable());
  }

  public Page<PostEntity> getSearchPostPaging(String subject, int page) {
    return getSearchPostPaging(subject, getPageable(page));
  }

  public Page<PostEntity> getSearchPostPaging(String subject, Pageable pageable) {
    if (StringUtils.isEmpty(subject)) {
      return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }

    Page<PostEntity> page = postRepository.findAllBySubjectContaining(subject, pageable);
    return new PageImpl<>(getImmutable(page.getContent()), pageable, page.getTotalElements());
  }

  public PostEntity save(PostEntity postEntity) {
    return postRepository.save(postEntity);
  }

  public PostEntity getPost(long id) {
    return postRepository.findById(id);
  }

  public void delete(long id) {
    postRepository.deleteById(id);
  }
}
