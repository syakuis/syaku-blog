package org.syaku.blog.post.web;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 15.
 */

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Base64;

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

  String authorization = "Authorization";
  String username = "admin";
  String password = "1234";
  String token;

  @Before
  public void setup() {
    assertNotNull(postService);
    objectMapper = new ObjectMapper();

    postService.save(PostEntity.builder().subject("제목").contents("내용").build());

    String token = username + ":" + password;
    this.token = "Basic " + Base64.getEncoder().encodeToString(token.getBytes());
  }

  @Test
  public void 쓰기() throws Exception {
    this.mvc.perform(
      post("/post")
        .header(authorization, this.token)
        .with(csrf())
        .content(objectMapper.writeValueAsString(
          PostEntity.builder().subject("쓰기_제목").contents("쓰기_내용").build()))
        .contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
      .andExpect(jsonPath("$.subject").value("쓰기_제목"))
      .andExpect(jsonPath("$.contents").value("쓰기_내용"));
  }

  @Test
  public void 목록() throws Exception {
    this.mvc.perform(
      get("/post")
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
      .andExpect(content().json(objectMapper.writeValueAsString(postService.getPostPaging())));
  }

  @Test
  public void 보기() throws Exception {
    Post post = postService.getPost(1);

    this.mvc.perform(get("/post/{id}", 1))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
      .andExpect(content().json(objectMapper.writeValueAsString(post)));
  }

  @Test
  public void 삭제() throws Exception {
    assertNotNull(postService.getPost(1));
    this.mvc.perform(delete("/post/{id}", 1))
      .andExpect(status().isOk());

    assertNull(postService.getPost(1));
  }
}
