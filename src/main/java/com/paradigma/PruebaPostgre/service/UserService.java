package com.paradigma.PruebaPostgre.service;


import com.paradigma.PruebaPostgre.entity.UserLogin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public interface UserService {

  CompletionStage<String> addUser(UserLogin user);

  CompletionStage<List<UserLogin>> getUsers();

  CompletionStage<List<UserLogin>> getUsersByName(String name);

}
