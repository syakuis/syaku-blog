package org.syaku.blog.user.web;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 25/10/2018
 */

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.syaku.blog.user.domain.UserEntity;
import org.syaku.blog.user.repository.UserRepository;
import org.syaku.blog.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
  @Autowired private MockMvc mvc;

  @Test
  public void 일반_인증() throws Exception {
    this.mvc.perform(
      get("/user").with(httpBasic("admin", "1234"))
        .contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    )
      .andExpect(status().isOk());
  }
}
