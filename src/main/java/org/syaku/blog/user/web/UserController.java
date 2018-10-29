package org.syaku.blog.user.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 29/10/2018
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping
  public User info(Authentication authentication) {
    if (authentication == null) {
      throw new UsernameNotFoundException("사용자 정보가 없습니다.");
    }

    return (User) authentication.getPrincipal();
  }
}
