package com.example.cache;

import com.example.cache.entity.User;
import com.example.cache.entity.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  private final UserRepository repository;

  @Cacheable(value = "userCache", key = "#userId")
  public Optional<User> getUser(String userId) {
    log.info("DB 조회: {}", userId);
    return repository.findById(userId);
  }

  @CachePut(value = "userCache", key = "#user.userId")
  public User insertUser(User user) {
    if (repository.existsById(user.getUserId())) {
      throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
    }
    return repository.save(user);
  }

  @CachePut(value = "userCache", key = "#user.userId")
  public User updateUser(User user) {
    if (!repository.existsById(user.getUserId())) {
      throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
    }
    return repository.save(user);
  }

  @CacheEvict(value = "userCache", key = "#userId")
  public void deleteUser(String userId) {
    repository.deleteById(userId);
  }

  @CacheEvict(value = "userCache", allEntries = true)
  public void resetCache() {
    log.info("Cache reset");
  }
}
