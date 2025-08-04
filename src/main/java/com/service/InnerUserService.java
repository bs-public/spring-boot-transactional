package com.service;

import com.entity.Users;
import com.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InnerUserService {

  private final UserRepository userRepository;

  public InnerUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void saveInRequiredTransaction(String name) {
    Users user = new Users();
    user.setName(name);
    userRepository.save(user);
    System.out.println("Inner: Saved in REQUIRED");
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void saveInNewTransaction(String name) {
    Users user = new Users();
    user.setName(name);
    userRepository.save(user);
    System.out.println("Inner: Saved in REQUIRES_NEW");
    // Uncomment below to test rollback behavior
    // throw new RuntimeException("Inner failed");
  }

  @Transactional(propagation = Propagation.NESTED)
  public void saveInNestedTransaction(String name) {
    Users user = new Users();
    user.setName(name);
    userRepository.save(user);
    System.out.println("Inner (NESTED): Saved");

    // Simulate failure inside nested block
    throw new RuntimeException("Nested transaction failed");
  }

  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void saveOutsideTransaction(String name) {
    Users user = new Users();
    user.setName(name + "_not_supported");
    userRepository.save(user);

    System.out.println("Inner (NOT_SUPPORTED): Committed independently");
  }
}
