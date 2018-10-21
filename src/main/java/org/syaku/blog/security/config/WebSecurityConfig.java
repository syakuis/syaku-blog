package org.syaku.blog.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 20/10/2018
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Autowired
  private SecurityProperties securityProperties;

  @Bean
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("1234").roles("USER").build());
    return manager;
  }

  @Configuration
  protected static class Security extends WebSecurityConfigurerAdapter {

    /**
     * 보안 시작지점. 권한이 없는 지 판단하여 페이지를 이동한다.
     * @return
     */
    private AuthenticationEntryPoint authenticationEntryPoint() {
      return new BasicAuthenticationEntryPoint();
    }

    private BasicAuthenticationFilter basicAuthenticationFilter(
      AuthenticationManager authenticationManager) {
      return new BasicAuthenticationFilter(authenticationManager, authenticationEntryPoint());
    }

    /**
     * {@link SecurityWebFilterChain} 을 이용한 인증 보안 시나리오 구성.
     * 스프링 시큐리티에서 제공하는 시나리오를 사용하지 않고 직접 모든 필터를 구성할 것이다.
     * 백엔드 Api 서버만 구현하고 클라이언트 UI 는 프론트엔드에서 구현하기 때문에는 로그인페이지가 필요하지 않는 다.
     * formLogin 이나 loginPage, usernameParamerter, passwordParameter 는 필요없다.
     * 일단 basicAuthentication 으로 구현할 것이고 클라이언트에서 계정과 암호가 암호화되어 서버에 전달된다.
     * @param http ServerHttpSecurity
     * @return SecurityWebFilterChain
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
//        .httpBasic().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/post").hasRole("USER")
        .anyRequest()
        .authenticated()
        .and()
        .addFilterAt(basicAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class)
        .csrf();
    }
  }
}
