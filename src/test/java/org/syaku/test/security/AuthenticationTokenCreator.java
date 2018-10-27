package org.syaku.test.security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 22/10/2018
 */
@AllArgsConstructor
public class AuthenticationTokenCreator {
  private final String username;
  private final String password;

  public HttpHeaders basic() {
    HttpHeaders httpHeaders = new HttpHeaders();
    String token = username + ":" + password;
    httpHeaders.add("Authorization", "Basic " + Base64Utils.encodeToString(token.getBytes()));
    return httpHeaders;
  }
}
