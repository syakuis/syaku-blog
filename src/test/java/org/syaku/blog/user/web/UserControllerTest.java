package org.syaku.blog.user.web;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 25/10/2018
 */

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.syaku.blog.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
  @Autowired
  private UserService userService;

  @Autowired
  private MockMvc mvc;

  @Before
  public void setup() {
    userService.saveUser(UserEntity.builder()
      .username("admin").password("1234").email("syaku@naver.com").build());
  }

  @After
  public void exit() {
    userService.deleteUserByUsername("admin");
  }

  private String getToken(String username) {
    UserEntity userEntity = userService.getUserByUsername(username);
    String token = userEntity.getUsername() + ":" + userEntity.getPassword();
    return "Basic " + Base64.getEncoder().encodeToString(token.getBytes());
  }

  @Test
  public void 사용자인증허가() throws Exception {
    this.mvc.perform(
      get("/user")
        .header("Authorization", getToken("admin"))
        .contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    )
      .andExpect(status().isOk()).andExpect(jsonPath("$.username").value("admin"));
  }
}
