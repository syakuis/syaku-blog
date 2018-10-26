package org.syaku.blog.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.syaku.blog.user.domain.UserEntity;
import org.syaku.blog.user.repository.UserRepository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 23/10/2018
 */
@Service
@Transactional
public class UserService {
  private UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public long getCountUser() {
    return userRepository.count();
  }

  public UserEntity saveUser(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  public UserEntity getUserByUsername(String username) {
    return userRepository.findOneByUsername(username);
  }

  public void deleteUserByUsername(String username) {
    userRepository.deleteByUsername(username);
  }
}
