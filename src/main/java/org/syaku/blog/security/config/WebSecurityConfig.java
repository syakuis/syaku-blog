package org.syaku.blog.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 20/10/2018
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Configuration
  static class Security extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
      return new BasicAuthenticationEntryPoint();
    }

    private BasicAuthenticationFilter basicAuthenticationFilter(
      AuthenticationManager authenticationManager) {
      return new BasicAuthenticationFilter(authenticationManager, authenticationEntryPoint());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      if (securityProperties.isEnableCsrf()) {
        http.csrf();
      } else {
        http.csrf().disable();
      }
      http
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/post/**").permitAll()
        .antMatchers(HttpMethod.POST, "/post/*").hasRole("USER")
        .antMatchers(HttpMethod.DELETE, "/post/*").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .addFilterAt(basicAuthenticationFilter(authenticationManagerBean()), BasicAuthenticationFilter.class);
    }
  }
}
