package org.syaku.blog.post.service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 9.
 */

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.blog.post.domain.PostEntity;
import org.syaku.blog.post.repository.PostRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CrudPostServiceTest {
  @Autowired
  private PostRepository postRepository;

  @Before
  public void setup() {
    postRepository.save(
      PostEntity.builder()
        .subject("제목")
        .contents("내용")
        .build());
  }

  @Test
  public void 전체데이터가져오기() {
    assertFalse(postRepository.findAll().isEmpty());
  }
}
