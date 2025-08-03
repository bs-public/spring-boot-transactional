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
}
