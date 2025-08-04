package com.controller;

import com.dto.UserRequest;
import com.service.OuterUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tx")
public class TxPropagationController {

  private final OuterUserService outerUserService;

  public TxPropagationController(OuterUserService outerUserService) {
    this.outerUserService = outerUserService;
  }

  @PostMapping("/required")
  public String callRequired(@RequestBody UserRequest request) {
    outerUserService.outerMethodWithRequiredInner(request.getUsername());
    return "Called REQUIRED";
  }

  @PostMapping("/requires-new")
  public String callRequiresNew(@RequestBody UserRequest request) {
    outerUserService.outerMethodWithNewInner(request.getUsername());
    return "Called REQUIRES_NEW";
  }

  @PostMapping("/nested")
  public String callNested(@RequestBody UserRequest request) {
    outerUserService.outerMethodWithNestedInner(request.getUsername());
    return "Called NESTED";
  }
}
