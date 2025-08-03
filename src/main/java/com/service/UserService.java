package com.service;

import com.entity.Users;
import com.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<Users> getAllUsers() {
    return userRepository.findAll();
  }

  @Transactional
  public void saveUserWithSuccess(String username) {
    Users user = new Users();
    user.setName(username);
    userRepository.save(user);
  }

  @Transactional
  public void saveUserWithFailure(String username) {
    Users user = new Users();
    user.setName(username);
    userRepository.save(user);
    throw new RuntimeException("Simulated exception"); // rollback
  }

  @Transactional(readOnly = true)
  public List<Users> getAllUsersReadOnly() {
    return userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public void attemptWriteInReadOnlyTransaction(String username) {
    Users user = new Users();
    user.setName(username);
    userRepository.save(user); // Will fail in some DBs, no-op in others
  }
}
