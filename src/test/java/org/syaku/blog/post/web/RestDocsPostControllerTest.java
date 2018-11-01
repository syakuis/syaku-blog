package org.syaku.blog.post.web;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.syaku.blog.post.domain.PostEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 01/11/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestDocsPostControllerTest {
  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Rule
  public JUnitRestDocumentation restDocumentation =
    new JUnitRestDocumentation("build/generated-snippets");

  @Before
  public void setup() {
    this.objectMapper = new ObjectMapper();
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
      .defaultRequest(MockMvcRequestBuilders.post("/post")
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .alwaysExpect(status().isOk())
      .alwaysExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
      .alwaysDo(
        document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
      .apply(documentationConfiguration(this.restDocumentation))
      .build();
  }

  @Test
  public void post() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.post("/post")
      .content(objectMapper.writeValueAsString(
        PostEntity.builder().subject("제목").contents("내용").build())));
  }
}
