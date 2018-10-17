package org.syaku.blog.post.web;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 15.
 */

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.syaku.blog.post.domain.Post;
import org.syaku.blog.post.domain.PostEntity;
import org.syaku.blog.post.service.PostService;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private PostService postService;

  @Before
  public void setup() {
    assertNotNull(postService);
    objectMapper = new ObjectMapper();
  }

  @Test
  public void test() throws Exception {
    // 쓰기 테스트
    this.mvc.perform(
      post("/post")
        .content(objectMapper.writeValueAsString(PostEntity.builder().subject("제목").contents("내용").build()))
        .contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    )
      .andExpect(content().contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
      .andExpect(status().isOk());

    List<Post> posts = postService.getPostList();

    // 목록 테스트
    this.mvc.perform(
      get("/post")
    )
      .andExpect(content().contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
      .andExpect(content().json(objectMapper.writeValueAsString(posts)))
      .andExpect(status().isOk());

    Post post = postService.getPost(1);

    // 보기 테스트
    this.mvc.perform(get("/post/{id}", 1))
      .andExpect(content().contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
      .andExpect(content().json(objectMapper.writeValueAsString(post)))
      .andExpect(status().isOk());

    this.mvc.perform(delete("/post/{id}", 1))
      .andExpect(status().isOk());

    assertTrue(postService.getPostList().isEmpty());
  }
}
