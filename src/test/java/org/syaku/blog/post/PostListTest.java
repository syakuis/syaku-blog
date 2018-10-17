package org.syaku.blog.post;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 16/10/2018
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.syaku.blog.post.domain.Post;
import org.syaku.blog.post.domain.PostEntity;
import org.syaku.blog.post.service.PostService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostListTest {
  @Autowired
  private PostService postService;

  /**
   * 여러 개의 포스트를 미리 등록하고 테스트한다.
   */
  @Before
  public void setup() {
    for (int i = 0; i < 1000; i++) {
      postService.save(PostEntity.builder().subject("a"+i).contents("내용").build());
    }
  }

  @Test
  public void 등록_내림차순_정렬_테스트() {
    List<Post> posts = postService.getPostList();
    assertTrue(posts.size() == 1000);

    Post firstPost = posts.get(0);
    assertEquals(firstPost.getSubject(), "a999" );
  }
}
