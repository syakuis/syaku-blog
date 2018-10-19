package org.syaku.blog.post.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.syaku.blog.post.domain.Post;
import org.syaku.blog.post.domain.PostEntity;
import org.syaku.blog.post.service.PostService;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 15.
 */
@RestController
@RequestMapping("/post")
public class PostController {
  @Autowired
  private PostService postService;

  @GetMapping("")
  public Page<Post> list(
    @RequestParam(name = "page", defaultValue = "0", required = false) int page) {
    return postService.getPostPaging(page);
  }

  @GetMapping(value = "", params = "subject")
  public Page<Post> search(
    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
    @RequestParam(name = "subject", defaultValue = "", required = false) String subject) {
    return postService.getSearchPostPaging(subject, page);
  }

  @GetMapping("/{id}")
  public Post view(@PathVariable("id") long id) {
    return postService.getPost(id);
  }

  @PostMapping("")
  public Post post(@RequestBody PostEntity post) {
    return postService.save(post);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") long id) {
    postService.delete(id);
  }
}
