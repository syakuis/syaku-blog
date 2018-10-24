package org.syaku.blog.user.domain;

import java.sql.Timestamp;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 23/10/2018
 */
public interface User {
  Long getId();

  String getUsername();

  String getPassword();

  String getEmail();

  Timestamp getCreationDateTime();
}
