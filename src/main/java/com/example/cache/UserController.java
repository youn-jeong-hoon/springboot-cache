package com.example.cache;

import com.example.cache.entity.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
  public final UserService service;

  @GetMapping("/{id}")
  public Optional<User> getUser(@PathVariable String id) {
    return service.getUser(id);
  }

  @PostMapping
  public User insertUser(@RequestBody User user) {
    return service.insertUser(user);
  }

  @PutMapping
  public User updateUser(@RequestBody User user) {
    return service.updateUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id) {
    service.deleteUser(id);
  }

  @GetMapping("/reset")
  public void resetCache() {
    service.resetCache();
  }
}
