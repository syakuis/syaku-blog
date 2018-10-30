package org.syaku.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.blog.post.domain.PostEntity;
import org.syaku.blog.post.repository.PostRepository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 29/10/2018
 */
@Profile("post-data-initialize")
@Component
@Transactional
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
  @Autowired
  private PostRepository postRepository;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      postRepository.save(PostEntity.builder()
        .subject(stringBuilder.append("a").append(i).append(i % 2 == 0 ? "search" : "").toString())
        .contents("내용").build());
      stringBuilder.setLength(0);
    }
  }
}
