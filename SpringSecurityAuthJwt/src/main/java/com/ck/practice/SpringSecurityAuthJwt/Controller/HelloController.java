package com.ck.practice.SpringSecurityAuthJwt.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//HelloController.java
@RestController
public class HelloController {

 @GetMapping("/public/hello")
 public String publicHello() {
     return "Hello, world (public)";
 }

 @GetMapping("/api/user")
 public String userHello(Authentication authentication) {
     return "Hello, " + authentication.getName();
 }

 @GetMapping("/api/admin")
 @PreAuthorize("hasRole('ADMIN')")
 public String adminHello() {
     return "Hello, admin";
 }
}

