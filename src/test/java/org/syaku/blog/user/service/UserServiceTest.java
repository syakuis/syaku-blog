package org.syaku.blog.user.service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 23/10/2018
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.blog.user.domain.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
  @PersistenceContext
  private EntityManager entityManager;
  @Autowired
  private UserService userService;

  private UserEntity userEntity;

  @Before
  public void setup() {
    userEntity = userService.saveUser(UserEntity.builder()
      .username("test").password("1234").email("test@naver.com").build());
  }

  @After
  @Rollback
  public void exit() {

  }

  @Test
  public void 사용자등록() {
    UserEntity user = userService.saveUser(UserEntity.builder()
      .username("syaku").password("1234").email("syaku@test.com").build());

    assertEquals(user, userService.getUserByUsername("syaku"));
    assertSame(user, userService.getUserByUsername("syaku"));
  }

  @Test(expected = PersistenceException.class)
  public void 사용자중복검사() {
    userService.saveUser(UserEntity.builder()
      .username("test").password("1234").email("test@naver.com").build());

    entityManager.flush();
  }

  @Test
  public void 사용자수정() {
    userEntity.setPassword("3333");
    userEntity = userService.saveUser(userEntity);

    assertEquals(userEntity, userService.getUserByUsername(userEntity.getUsername()));
    assertSame(userEntity, userService.getUserByUsername(userEntity.getUsername()));
  }

  @Test
  public void 사용자삭제() {
    userService.deleteUserByUsername(userEntity.getUsername());

    assertEquals(userService.getUserByUsername(userEntity.getUsername()), null);
  }
}
