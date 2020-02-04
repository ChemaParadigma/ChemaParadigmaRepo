package com.paradigma.PruebaPostgre.service.impl;

import com.paradigma.PruebaPostgre.entity.UserLogin;
import com.paradigma.PruebaPostgre.repository.UserRepository;
import com.paradigma.PruebaPostgre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public CompletionStage<String> addUser(UserLogin user) {
    return CompletableFuture.supplyAsync(() -> userRepository.saveAndFlush(user))
      .thenApplyAsync(result -> "User " + user.getName() + " is saved") ;
  }

  @Override
  public CompletionStage<List<UserLogin>> getUsers() {
    return CompletableFuture.supplyAsync(() -> userRepository.findAll());
  }

  @Override
  public CompletionStage<List<UserLogin>> getUsersByName(String name) {
    return CompletableFuture.supplyAsync(() -> userRepository.findByName(name));  }
}
