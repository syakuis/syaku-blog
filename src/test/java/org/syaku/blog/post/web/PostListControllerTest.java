package org.syaku.blog.post.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.syaku.blog.post.domain.PostEntity;
import org.syaku.blog.post.repository.PostRepository;
import org.syaku.blog.post.service.PostService;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 18/10/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostListControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private PostService postService;

  @Autowired
  private PostRepository postRepository;

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

  private Pageable getPageable() {
    return PageRequest.of(1, 10, new Sort(Sort.Direction.DESC, "id"));
  }

  @After
  public void exit() {
    postRepository.deleteAll();
  }

  @Test
  public void 페이징_테스트() throws Exception {
    this.mvc.perform(get("/post"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(10)))
      .andExpect(jsonPath("$.content[0].subject", is("a999")))
      .andExpect(jsonPath("$.totalElements", is(1000)))
      .andExpect(jsonPath("$.totalPages", is(100)));
  }

  @Test
  public void 검색_테스트() throws Exception {
    this.mvc.perform(get("/post").param("subject", "search"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(10)))
      .andExpect(jsonPath("$.content[0].subject", is("a998search")))
      .andExpect(jsonPath("$.totalElements", is(500)))
      .andExpect(jsonPath("$.totalPages", is(50)));
  }

  @Test
  public void 검색어_없는_테스트() throws Exception {
    this.mvc.perform(get("/post").param("subject", ""))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(0)))
      .andExpect(jsonPath("$.totalElements", is(0)))
      .andExpect(jsonPath("$.totalPages", is(0)));
  }

  @Test
  public void 페이지_변경_테스트() throws Exception {
    this.mvc.perform(get("/post").param("page", "50"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(10)))
      .andExpect(jsonPath("$.content[0].subject", is("a499")))
      .andExpect(jsonPath("$.number", is(50)))
      .andExpect(jsonPath("$.totalElements", is(1000)))
      .andExpect(jsonPath("$.totalPages", is(100)));
  }

  @Test
  public void 검색_페이지_변경_테스트() throws Exception {
    this.mvc.perform(get("/post")
      .param("subject", "search")
      .param("page", "20"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(10)))
      .andExpect(jsonPath("$.content[0].subject", is("a598search")))
      .andExpect(jsonPath("$.number", is(20)))
      .andExpect(jsonPath("$.totalElements", is(500)))
      .andExpect(jsonPath("$.totalPages", is(50)));
  }
}
