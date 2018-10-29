package org.syaku.blog.post.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.syaku.blog.post.domain.PostEntity;
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

  @Test
  public void 페이징_테스트() throws Exception {
    Page<PostEntity> page = postService.getPostPaging();
    this.mvc.perform(get("/post"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(page.getSize())))
      .andExpect(jsonPath("$.content[0].subject", is(page.getContent().get(0).getSubject())))
      .andExpect(jsonPath("$.number", is(page.getNumber())))
      .andExpect(jsonPath("$.totalElements", is((int) page.getTotalElements())))
      .andExpect(jsonPath("$.totalPages", is(page.getTotalPages())));
  }

  @Test
  public void 검색_테스트() throws Exception {
    Page<PostEntity> page = postService.getSearchPostPaging("search");
    this.mvc.perform(get("/post").param("subject", "search"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(page.getSize())))
      .andExpect(jsonPath("$.content[0].subject", is(page.getContent().get(0).getSubject())))
      .andExpect(jsonPath("$.number", is(page.getNumber())))
      .andExpect(jsonPath("$.totalElements", is((int) page.getTotalElements())))
      .andExpect(jsonPath("$.totalPages", is(page.getTotalPages())));
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
    Page<PostEntity> page = postService.getPostPaging(50);

    this.mvc.perform(get("/post").param("page", "50"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(page.getSize())))
      .andExpect(jsonPath("$.content[0].subject", is(page.getContent().get(0).getSubject())))
      .andExpect(jsonPath("$.number", is(page.getNumber())))
      .andExpect(jsonPath("$.totalElements", is((int) page.getTotalElements())))
      .andExpect(jsonPath("$.totalPages", is(page.getTotalPages())));
  }

  @Test
  public void 검색_페이지_변경_테스트() throws Exception {
    Page<PostEntity> page = postService.getSearchPostPaging("search", 20);
    this.mvc.perform(get("/post")
      .param("subject", "search")
      .param("page", "20"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .andExpect(jsonPath("$.content", hasSize(page.getSize())))
      .andExpect(jsonPath("$.content[0].subject", is(page.getContent().get(0).getSubject())))
      .andExpect(jsonPath("$.number", is(page.getNumber())))
      .andExpect(jsonPath("$.totalElements", is((int) page.getTotalElements())))
      .andExpect(jsonPath("$.totalPages", is(page.getTotalPages())));
  }
}
