package org.syaku.blog.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.syaku.blog.post.domain.PostEntity;
import org.syaku.blog.post.service.PostService;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 10/11/2018
 */
public class PostListTestListener extends AbstractTestExecutionListener {
  private PostService postService;

  private List<PostEntity> postEntities;

  @Override
  public void beforeTestClass(TestContext testContext) {
    this.postService = testContext.getApplicationContext().getBean("postService", PostService.class);

    StringBuilder stringBuilder = new StringBuilder();
    postEntities = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      postEntities.add(PostEntity.builder()
        .subject(stringBuilder.append("a").append(i).append(i % 2 == 0 ? "search" : "").toString())
        .contents("내용").build());
      stringBuilder.setLength(0);
    }

    postService.save(postEntities);
  }

  @Override
  public void afterTestClass(TestContext testContext) {
    postService.delete(postEntities);
  }
}
