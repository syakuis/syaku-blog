package org.syaku.blog.post.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 9.
 */
@Entity
@Table(name = "POST")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter(AccessLevel.NONE)
@Builder
public class PostEntity implements Comparable<PostEntity> {
  @Id
  @Column(nullable = false)
  @SequenceGenerator(
    name = "POST_ID_GEN",
    sequenceName = "POST_ID_SEQ",
    allocationSize = 1)
  @GeneratedValue(generator = "POST_ID_GEN", strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(nullable = false)
  private String subject;

  @Column
  @Lob
  private String contents;

  @Column(name = "CREATION_DATETIME", nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime creationDateTime;

  @Column(name = "MODIFICATION_DATETIME")
  @UpdateTimestamp
  private LocalDateTime modificationDateTime;

  @Override
  public int compareTo(PostEntity o) {
    if (id > o.getId()) {
      return 1;
    } else if (id < o.getId()) {
      return -1;
    } else {
      return 0;
    }
  }
}
