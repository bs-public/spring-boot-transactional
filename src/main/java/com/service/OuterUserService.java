package com.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OuterUserService {

  private final InnerUserService innerUserService;

  public OuterUserService(InnerUserService innerUserService) {
    this.innerUserService = innerUserService;
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
}
