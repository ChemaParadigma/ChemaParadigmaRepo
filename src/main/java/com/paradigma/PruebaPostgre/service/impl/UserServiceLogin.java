package com.paradigma.PruebaPostgre.service.impl;

import com.paradigma.PruebaPostgre.mapper.BuilderUser;
import com.paradigma.PruebaPostgre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceLogin implements UserDetailsService {

    @Autowired
    private BuilderUser builderUser;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        return userService.getUserByUserName(userName)
                .thenApplyAsync(user -> builderUser.convertToUser(user)).
                        toCompletableFuture().join();
    }
}
