package com.paradigma.PruebaPostgre.service;


import com.paradigma.PruebaPostgre.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public interface UserService {

  CompletionStage<String> addUser(User user);

  CompletionStage<List<User>> getUsers();

  CompletionStage<User> getUserByUserName(String userName);

}
