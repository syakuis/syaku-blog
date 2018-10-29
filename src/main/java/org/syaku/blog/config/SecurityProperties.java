package org.syaku.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 21/10/2018
 */
@Configuration
@ConfigurationProperties(prefix = "blog.security")
@PropertySource("classpath:org/syaku/blog/config/security.properties")
@Data
public class SecurityProperties {
  private boolean enableCsrf;
}
