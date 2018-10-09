package org.syaku.blog.post.domain;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 9.
 */
public interface Post {
  long getId();

  String getSubject();

  String getContents();

  java.util.Date getCreationDate();

  java.util.Date getModificationDate();
}
