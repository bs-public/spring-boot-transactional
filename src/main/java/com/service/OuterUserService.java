package com.service;

import com.entity.Users;
import com.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OuterUserService {

  private final InnerUserService innerUserService;
  private final UserRepository userRepository;

  public OuterUserService(InnerUserService innerUserService, UserRepository userRepository) {
    this.innerUserService = innerUserService;
    this.userRepository = userRepository;
  }

  @Transactional
  public void outerMethodWithRequiredInner(String userName) {
    System.out.println("Outer: Start");

    innerUserService.saveInRequiredTransaction(userName);

    if (true) {
      throw new RuntimeException("Outer failed after inner");
    }
  }

  @Transactional
  public void outerMethodWithNewInner(String userName) {
    System.out.println("Outer: Start");

    innerUserService.saveInNewTransaction(userName);

    if (true) {
      throw new RuntimeException("Outer failed after inner");
    }
  }

  @Transactional
  public void outerMethodWithNestedInner(String userName) {
    System.out.println("Outer: Start");

    try {
      // call to NESTED - Spring asks the DB to create a savepoint if db supports
      innerUserService.saveInNestedTransaction(userName + "_inner");
    } catch (Exception e) {
      // Spring automatically rolls back to the savepoint created
      System.out.println("Caught inner failure: " + e.getMessage());
    }

    Users user = new Users();
    user.setName(userName + "_outer");
    userRepository.save(user);

    System.out.println("Outer: Completed");
  }

  @Transactional
  public void outerMethodWithNotSupported(String username) {
    System.out.println("Outer: Transaction started");

    innerUserService.saveOutsideTransaction(username);

    // Outer will fail
    throw new RuntimeException("Outer failed");
  }
}
