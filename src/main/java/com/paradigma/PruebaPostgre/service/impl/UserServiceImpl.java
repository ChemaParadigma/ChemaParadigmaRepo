package com.paradigma.PruebaPostgre.service.impl;

import com.paradigma.PruebaPostgre.entity.User;
import com.paradigma.PruebaPostgre.repository.UserRepository;
import com.paradigma.PruebaPostgre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public CompletionStage<String> addUser(User user) {
        return CompletableFuture.supplyAsync(() -> userRepository.save(user))
                .thenApplyAsync(result -> "User " + user.getUserName() + " is saved");
    }

    @Override
    public CompletionStage<List<User>> getUsers() {
        return CompletableFuture.supplyAsync(() -> userRepository.findAll());
    }

    @Override
    public CompletionStage<User> getUserByUserName(String userName) {
        return CompletableFuture.supplyAsync(() -> userRepository.findByUserName(userName));
    }
}
