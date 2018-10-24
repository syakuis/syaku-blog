package org.syaku.blog.user.service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 23/10/2018
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.blog.user.domain.User;
import org.syaku.blog.user.domain.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
  @Autowired
  private EntityManager entityManager;
  @Autowired
  private UserService userService;

  @Test
  public void 사용자등록() {
    User user = userService.signup(UserEntity.builder()
      .username("admin").password("1234").email("syaku@naver.com").build());

    assertEquals(user, userService.getUserByUsername("admin"));
    assertSame(user, userService.getUserByUsername("admin"));
  }

  @Test(expected = PersistenceException.class)
  public void 사용자중복검사() {
    userService.signup(UserEntity.builder()
      .username("test").password("1234").email("test@naver.com").build());

    userService.signup(UserEntity.builder()
      .username("test").password("1234").email("test@naver.com").build());

    entityManager.flush();
  }
}
