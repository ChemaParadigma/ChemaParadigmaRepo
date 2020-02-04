package com.paradigma.PruebaPostgre.controller;

import com.paradigma.PruebaPostgre.entity.UserLogin;
import com.paradigma.PruebaPostgre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public CompletionStage<String> addUser(@RequestBody UserLogin user) {
    return userService.addUser(user);
  }

  @GetMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
  public CompletionStage<List<UserLogin>> getUsers() {
    return userService.getUsers();
  }

  @GetMapping(value = "/getUser/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CompletionStage<List<UserLogin>> getUsersByName(@PathVariable("name") String name) {
    return userService.getUsersByName(name);
  }
}
