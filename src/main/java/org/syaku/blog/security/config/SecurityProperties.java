package org.syaku.blog.security.config;

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
@PropertySource("classpath:org/syaku/blog/security/config/security.properties")
@Data
public class SecurityProperties {
  private boolean enableCsrf;
  private String usernameParameter;
  private String passwordParameter;
  private String loginUrl;
  private String logoutUrl;
  private String cookieName;
  private boolean alwaysUseDefaultTargetUrl;
  private String defaultTargetUrl;
  private int maximumSessions;
  private boolean exceptionIfMaximumExceeded;
}
