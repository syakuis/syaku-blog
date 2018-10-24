package org.syaku.blog.user.domain;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 23/10/2018
 */
@Entity
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity implements User {
  @Id
  @Column(nullable = false)
  @SequenceGenerator(
    name = "USER_ID_GEN",
    sequenceName = "USER_ID_SEQ",
    allocationSize = 1)
  @GeneratedValue(generator = "USER_ID_GEN", strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false, unique = true)
  @NotNull
  private String username;

  @Column(nullable = false)
  @NotNull
  private String password;

  @Column(nullable = false, unique = true)
  @NotNull
  private String email;

  @Column(nullable = false)
  @CreationTimestamp
  private Timestamp creationDateTime;
}
