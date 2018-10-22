package org.syaku.blog.security;

import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 22/10/2018
 */
public class TestAuthenticationToken {
  public static HttpHeaders getBasicAuthentication() {
    HttpHeaders httpHeaders = new HttpHeaders();
    String token = "admin:1234";
    httpHeaders.add("Authorization", "Basic " + Base64Utils.encodeToString(token.getBytes()));
    return httpHeaders;
  }
}
