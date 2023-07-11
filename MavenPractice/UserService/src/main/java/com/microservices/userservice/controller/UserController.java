package com.microservices.userservice.controller;

import com.microservices.userservice.entities.User;
import com.microservices.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  @PostMapping()
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User user1 = this.userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(user1);
  }
  @GetMapping("/{userId}")
//  @PreAuthorize ("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
  @RateLimiter ( name ="userRateLimiter", fallbackMethod = "ratingHotelFallBack")
  public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
    User user = this.userService.getUserById(userId);
    return ResponseEntity.ok(user);
  }
  @GetMapping()
//  @PreAuthorize ("hasAuthority('Admin')")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> allUser = userService.getAllUser();
    return ResponseEntity.ok(allUser);
  }

  // creating fall back method for circuit breaker

  public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {
    User dummyUser =
        User.builder()
            .userName("Dummy")
            .email("dummy@gmail.com")
            .about("This is dummy data because service is down")
            .build();
    return new ResponseEntity<> ( dummyUser, HttpStatus.BAD_REQUEST );
  }
}
