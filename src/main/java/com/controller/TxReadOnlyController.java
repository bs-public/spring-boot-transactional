package com.controller;

import com.dto.UserRequest;
import com.entity.Users;
import com.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TxReadOnlyController {

  private final UserService userService;

  public TxReadOnlyController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/readonly")
  public List<Users> getUsersReadOnly() {
    return userService.getAllUsersReadOnly();
  }

  @PostMapping("/users/readonly-write")
  public String attemptWriteInReadOnly(@RequestBody UserRequest request) {
    userService.attemptWriteInReadOnlyTransaction(request.getUsername());
    return "Ok";
  }
}
