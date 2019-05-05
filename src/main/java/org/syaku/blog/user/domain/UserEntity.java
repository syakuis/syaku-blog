package org.syaku.blog.user.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 23/10/2018
 */
@Entity
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserEntity {
  @Id
  @SequenceGenerator(
    name = "USER_ID_GEN",
    sequenceName = "USER_ID_SEQ",
    allocationSize = 1)
  @GeneratedValue(generator = "USER_ID_GEN", strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotBlank
  @Column(nullable = false, unique = true)
  private String username;

  @Setter
  @NotBlank
  @Column(nullable = false)
  private String password;

  @NotBlank
  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, updatable = false)
  private LocalDateTime creationDatetime;

  @Column(insertable = false)
  private LocalDateTime modificationDatetime;

  @PrePersist
  protected void onPersist() {
    this.creationDatetime = LocalDateTime.now();
  }
  @PreUpdate
  protected void onUpdate() {
    this.modificationDatetime = LocalDateTime.now();
  }
}
