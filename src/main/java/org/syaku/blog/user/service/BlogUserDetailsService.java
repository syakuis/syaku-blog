package org.syaku.blog.user.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.blog.user.domain.UserEntity;
import org.syaku.blog.user.repository.UserRepository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 25/10/2018
 */
@Service("userDetailsService")
@Transactional(readOnly = true)
public class BlogUserDetailsService implements UserDetailsService {
  private UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findOneByUsername(username);
    if (userEntity == null) {
      throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
    }

    return new User(
      userEntity.getUsername(), userEntity.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
  }
}
