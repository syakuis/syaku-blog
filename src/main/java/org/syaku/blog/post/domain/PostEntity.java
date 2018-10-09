package org.syaku.blog.post.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 9.
 */
@Entity
@Table(name = "POST")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostEntity implements Post {
  @Id
  @Column(nullable = false)
  @SequenceGenerator(
    name = "POST_ID_GEN",
    sequenceName = "POST_ID_SEQ",
    allocationSize = 1)
  @GeneratedValue(generator = "POST_ID_GEN", strategy = GenerationType.SEQUENCE)
  private long id;

  @NotNull
  @Column(nullable = false, length = 255)
  private String subject;

  @Column
  @Lob
  private String contents;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;
}
