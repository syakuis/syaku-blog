package org.syaku.blog.user.service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 25/10/2018
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.syaku.blog.user.domain.UserEntity;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogUserDetailsServiceTest {
  @Autowired
  @Qualifier("userDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  private UserService userService;

  @Before
  public void setup() {
    userService.saveUser(UserEntity.builder().username("admin").password("1234").email("admin@admin").build());
  }

  @Test
  public void 사용자조회() {
    assertEquals(userDetailsService.loadUserByUsername("admin").getUsername(), "admin");
  }
}
