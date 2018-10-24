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
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
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
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      postService.save(PostEntity.builder()
        .subject(stringBuilder.append("a").append(i).append(i % 2 == 0 ? "search" : "").toString())
        .contents("내용").build());
      stringBuilder.setLength(0);
    }
  }

  @Test
  public void 등록_내림차순_정렬_테스트() {
    List<PostEntity> posts = postService.getPostList();
    assertTrue(posts.size() == 1000);

    PostEntity firstPost = posts.get(0);
    assertEquals(firstPost.getSubject(), "a999" );
  }

  @Test
  public void 페이지네비게이션_테스트() {
    Page<PostEntity> pagePost = postService.getPostPaging();
    assertEquals(pagePost.getTotalPages(), 100);
    assertEquals(pagePost.getSize(), 10);
    assertEquals(pagePost.getTotalElements(), 1000);
  }

  @Test
  public void 제목_검색_테스트() {
    Page<PostEntity> pagePost = postService.getSearchPostPaging("search");
    assertEquals(pagePost.getSize(), 10);
    assertEquals(pagePost.getTotalElements(), 500);
    assertEquals(pagePost.getTotalPages(), 50);
  }
}
