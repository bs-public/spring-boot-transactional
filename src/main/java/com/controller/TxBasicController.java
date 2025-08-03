package com.controller;

import com.dto.UserRequest;
import com.entity.Users;
import com.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TxBasicController {

  private final UserService userService;

  public TxBasicController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<Users> listUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/users/success")
  public String saveUserSuccessfully(@RequestBody UserRequest request) {
    userService.saveUserWithSuccess(request.getUsername());
    return "User saved successfully";
  }

  @PostMapping("/users/failure")
  public String saveUserWithError(@RequestBody UserRequest request) {
    userService.saveUserWithFailure(request.getUsername());
    return "ok";
  }
}
